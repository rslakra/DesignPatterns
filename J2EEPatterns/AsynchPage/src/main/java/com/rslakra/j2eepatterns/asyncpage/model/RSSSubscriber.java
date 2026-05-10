package com.rslakra.j2eepatterns.asyncpage.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A class to manage subscriptions to RSS pages
 */
public class RSSSubscriber extends Thread {
	/** the default update frequency. 30 seconds */
	private static final int UPDATE_FREQ = 30 * 1000;
	
	/**
	 * The internal representation of a subscription
	 */
	class RSSSubscription implements Comparable {
		/** the url to subscribe to */
		private String url;
		
		/** the time of the next update */
		private long nextUpdate;
		
		/** the update frequency for this subscription */
		private long updateFreq;
		
		/**
		 * Compare to another subscription. Subscriptions sort based on
		 * next update time
		 */
		public int compareTo(Object obj) {
			RSSSubscription rObj = (RSSSubscription) obj;
			if (rObj.nextUpdate > this.nextUpdate) {
				return -1;
			} else if (rObj.nextUpdate < this.nextUpdate) {
				return 1;
			} else {
				// if update time is the same, sort on URL
				return url.compareToIgnoreCase(rObj.url);
			}
		}
	}
	
	/** a set of subscription information, sorted by the next update time */
	private SortedSet subscriptions;
	
	/** existing subscriptions */
	private Map cache;
	
	/** when to quit */
	private boolean quit = false;
	
	/** singelton subscriber */
	private static RSSSubscriber subscriber;
	
	/* initialize singelton the singleton */
	static {
		subscriber = new RSSSubscriber();
		subscriber.start();
	}
	
	/**
	 * Get a reference to the subscriber. Use this instead of the
	 * constructor.
	 */
	public static RSSSubscriber getInstance() {
		return subscriber;
	}
	
	/**
	 * Constructor. Initialize data structures.
	 */
	RSSSubscriber() {
		subscriptions = new TreeSet();
		cache = Collections.synchronizedMap(new HashMap());
		
		// exit if we're the only thread left
		setDaemon(true);
	}
	
	/**
	 * Get an RSSInfo object from cache, or create a new
	 * subscritpion if its not in the cache.
	 */
	public RSSInfo getInfo(String url) throws Exception {
		if (cache.containsKey(url)) {
			return (RSSInfo) cache.get(url);
		}
		
		// add to cache
		RSSInfo rInfo = new RSSInfo();
		rInfo.parse(url);
		cache.put(url, rInfo);
		
		// create new subscription
		RSSSubscription newSub = new RSSSubscription();
		newSub.url = url;
		newSub.updateFreq = UPDATE_FREQ;
		putSubscription(newSub);
		
		return rInfo;
	}
	
	/**
	 * Add a subscription
	 */
	private synchronized void putSubscription(RSSSubscription subs) {
		subs.nextUpdate = System.currentTimeMillis() + subs.updateFreq;
		subscriptions.add(subs);
		notify();
	}
	
	/**
	 * Wait for next subscription to be ready for refresh
	 */
	private synchronized RSSSubscription getSubscription() {
		while (true) {
			while (subscriptions.size() == 0) {
				try {
					wait();
				} catch (InterruptedException ie) {
				}
			}
			
			RSSSubscription nextSub = (RSSSubscription) subscriptions.first();
			
			long curTime = System.currentTimeMillis();
			if (curTime >= nextSub.nextUpdate) {
				subscriptions.remove(nextSub);
				return nextSub;
			}
			
			try {
				wait(nextSub.nextUpdate - curTime);
			} catch (InterruptedException ie) {
			}
		}
	}
	
	/**
	 * Update subscriptions as they become ready for refresh
	 */
	public void run() {
		while (!quit) {
			RSSSubscription subs = getSubscription();
			
			try {
				RSSInfo rInfo = new RSSInfo();
				rInfo.parse(subs.url);
				cache.put(subs.url, rInfo);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			putSubscription(subs);
		}
	}
	
	/**
	 * Cause this subscriber to stop
	 */
	public void quit() {
		this.quit = true;
	}
}
