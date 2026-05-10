package com.devamatre.j2eepatterns.cachingfilter.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * A class to wrap a normal ServletResponse so that the result can be
 * cached.
 */
public class CacheResponseWrapper extends HttpServletResponseWrapper {
	/** the caching stream */
	private CacheOutputStream outStream;
	
	/** the replacement output stream and writer */
	private ServletOutputStream stream;
	private PrintWriter writer;
	
	/**
	 * A class to wrap a ByteArrayOutputStream as a servlet output
	 * stream
	 */
	class CacheOutputStream extends ServletOutputStream {
		/** the byte array output stream */
		private ByteArrayOutputStream bos;
		
		/**
		 * Constructor.
		 */
		CacheOutputStream() {
			bos = new ByteArrayOutputStream();
		}
		
		/**
		 * Write data to the underlying byte array output stream
		 */
		public void write(int param) throws IOException {
			bos.write(param);
		}
		
		/**
		 * Write data to the underlying byte array output stream
		 */
		public void write(byte[] b, int off, int len) throws IOException {
			bos.write(b, off, len);
		}
		
		/**
		 * Get the data we've written as a byte array
		 */
		protected byte[] getBytes() {
			return bos.toByteArray();
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setWriteListener(WriteListener writeListener) {
			if (writeListener == null) {
				throw new NullPointerException("writeListener");
			}
			try {
				writeListener.onWritePossible();
			} catch (IOException e) {
				writeListener.onError(e);
			}
		}
	}
	
	/**
	 * Constructor
	 */
	public CacheResponseWrapper(HttpServletResponse original) {
		super(original);
	}
	
	/**
	 * Create an instance of a CacheOutputStream to use
	 */
	protected ServletOutputStream createOutputStream() throws IOException {
		outStream = new CacheOutputStream();
		return outStream;
	}
	
	/**
	 * Get the replacement output stream
	 */
	public ServletOutputStream getOutputStream() throws IOException {
		if (stream != null) {
			return stream;
		}
		
		// make sure writer has not already been initialized
		if (writer != null) {
			throw new IOException("Writer already in use");
		}
		
		stream = createOutputStream();
		return stream;
	}
	
	/**
	 * Get the replacement writer
	 */
	public PrintWriter getWriter() throws IOException {
		if (writer != null) {
			return writer;
		}
		
		// make sure output stream has not already been initialized
		if (stream != null) {
			throw new IOException("OutputStream already in use");
		}
		
		writer = new PrintWriter(new OutputStreamWriter(createOutputStream(), StandardCharsets.UTF_8), true);
		return writer;
	}
	
	/**
	 * Get the cached data from this stream
	 */
	protected byte[] getBytes() throws IOException {
		// JSPs use getWriter(); buffered chars must be flushed before reading the backing stream.
		if (writer != null) {
			writer.flush();
		}
		if (outStream != null) {
			return outStream.getBytes();
		}
		
		return null;
	}
}
