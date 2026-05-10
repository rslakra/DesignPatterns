package com.devamatre.j2eepatterns.resourcepool;

import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.InputSource;

/**
 * A simple class to test our resource pool
 */
public class PoolTest implements Runnable {
	/** the default number of threads and pool size */
	public static final int DEFAULT_THREAD_COUNT = 100;
	public static final int DEFAULT_POOL_SIZE = 100;
	
	/** the maximum number of threads seen so far */
	private int max_thread_count;
	
	/** various thread sizes */
	private int started_thread_count;
	private int finished_thread_count;
	
	/** true if we're all done */
	private boolean finished;
	
	/** true if we had an error */
	private boolean had_error;
	
	/** the parser to use for the no pool test */
	private DocumentBuilderFactory dbf;
	
	/** the pool itself */
	private ResourcePool pool;
	
	/** Creates a new instance of PoolTest */
	public PoolTest(int nthreads, int poolsize) {
		System.out.println("Time = " + runPool(nthreads, poolsize));
	}
	
	/**
	 * Run a pool with a given number of threads and pool size. Return
	 * the amount of time it took
	 */
	private long runPool(int nthreads, int poolsize) {
		// create the pool or builder
		pool = new ResourcePool(new XMLParserFactory(), poolsize);
		// dbf = DocumentBuilderFactory.newInstance();
		
		// setup counters
		max_thread_count = nthreads;
		finished_thread_count = 0;
		finished = false;
		had_error = false;
		
		ThreadGroup group = new ThreadGroup("PoolTest");
		
		// create threads
		for (started_thread_count = 0; started_thread_count < max_thread_count; started_thread_count++) {
			(new Thread(group, this)).start();
		}
		
		long startTime = System.currentTimeMillis();
		
		// and we're off! Start all threads at once
		startCountNotify();
		
		// wait until all threads have finished
		while (finished_thread_count < max_thread_count) {
			Thread.yield();
		}
		
		long totalTime = System.currentTimeMillis() - startTime;
		
		// allow threads to exit all at once
		finishCountNotify();
		
		// clean up
		pool.destroy();
		if (had_error)
			return -1;
		
		return totalTime;
	}
	
	/**
	 * Run each individual thread
	 */
	public void run() {
		// wait until we are allowed to start
		startCountWait();
		
		try {
			// get the parser
			DocumentBuilder db = (DocumentBuilder) pool.getResource();
			// DocumentBuilder db = dbf.newDocumentBuilder();
			
			// parse
			if (!finished) {
				db.parse(new InputSource(new FileReader("long.xml")));
			}
			
			// return the resource
			pool.returnResource(db);
		} catch (Throwable t) {
			System.out.println("Error " + t.getMessage() + " encountered: terminating\n");
			had_error = true;
			finishCountNotify();
		} finally {
			finishCountWait();
		}
	}
	
	/**
	 * Wait until all threads are ready to start
	 */
	private synchronized void startCountWait() {
		while (started_thread_count < max_thread_count) {
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		}
	}
	
	/**
	 * Notify all threads we are starting
	 */
	private synchronized void startCountNotify() {
		notifyAll();
	}
	
	/**
	 * Wait until all threads are finished
	 */
	private synchronized void finishCountWait() {
		finished_thread_count++;
		
		while (!finished) {
			try {
				wait();
			} catch (InterruptedException ie) {
			}
		}
	}
	
	/**
	 * Notify threads they can exit
	 */
	private synchronized void finishCountNotify() {
		finished = true;
		notifyAll();
	}
	
	/**
	 * Run a test with a count of threads and pool size specified at
	 * the command line.
	 */
	public static void main(String[] args) {
		int nthreads = DEFAULT_THREAD_COUNT;
		int poolsize = DEFAULT_POOL_SIZE;
		
		if (args.length > 0) {
			nthreads = Integer.parseInt(args[0]);
		}
		
		if (args.length > 1) {
			poolsize = Integer.parseInt(args[1]);
		}
		
		if (nthreads <= 0 || poolsize <= 0) {
			System.out.println("Usage: PoolTest <number_of_threads>");
			System.exit(-1);
		}
		
		System.out.println("Running test with " + nthreads + " threads and " + poolsize + " entries in the pool");
		
		PoolTest pt = new PoolTest(nthreads, poolsize);
	}
	
}
