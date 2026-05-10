package com.devamatre.j2eepatterns.decorator.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

/**
 * Applies an XSLT stylesheet to XML responses that set the {@code XML-Encoded} header.
 * <p>
 * Must be mapped only to {@code *.xml} in {@code web.xml}. Mapping to {@code /*} breaks
 * Jetty welcome-file forwarding to JSP (mixed {@code getWriter} / {@code getOutputStream}).
 */
public class XmlStylesheetDecoratingFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ServletContext sc = filterConfig.getServletContext();
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String uri = httpRequest.getRequestURI();
		int q = uri.indexOf('?');
		if (q >= 0) {
			uri = uri.substring(0, q);
		}
		if (!uri.toLowerCase(Locale.ROOT).endsWith(".xml")) {
			chain.doFilter(request, response);
			return;
		}

		sc.log("XmlStylesheetDecoratingFilter (xml)");
		// Static /XMLView.xml has no XML-Encoded header; still decorate for browser HTML.
		boolean forceStylesheet = uri.toLowerCase(Locale.ROOT).endsWith("/xmlview.xml");
		BufferedXmlResponse wrapped = new BufferedXmlResponse((HttpServletResponse) response,
				"/SimpleTransform.xml", forceStylesheet);
		chain.doFilter(httpRequest, wrapped);
		wrapped.commitToClient();
	}

	@Override
	public void destroy() {
	}

	private final class BufferStream extends ServletOutputStream {
		private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

		@Override
		public void write(int b) {
			bos.write(b);
		}

		@Override
		public void write(byte[] b, int offset, int len) {
			bos.write(b, offset, len);
		}

		byte[] toByteArray() {
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
	 * Buffers the response body; supports both writer and output stream on the same buffer.
	 */
	private final class BufferedXmlResponse extends HttpServletResponseWrapper {
		private final HttpServletResponse orig;
		private final String transformPath;
		/** When true, run XSLT even if XML-Encoded header was not set (e.g. static XMLView.xml). */
		private final boolean forceStylesheet;
		private BufferStream cache;
		private ServletOutputStream stream;
		private PrintWriter writer;

		BufferedXmlResponse(HttpServletResponse response, String transformPath, boolean forceStylesheet) {
			super(response);
			this.orig = response;
			this.transformPath = transformPath;
			this.forceStylesheet = forceStylesheet;
		}

		private BufferStream buffer() {
			if (cache == null) {
				cache = new BufferStream();
			}
			return cache;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			if (writer != null) {
				writer.flush();
			}
			if (stream == null) {
				stream = buffer();
			}
			return stream;
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			if (writer == null) {
				writer = new PrintWriter(new OutputStreamWriter(buffer(), StandardCharsets.UTF_8), true);
			}
			return writer;
		}

		void commitToClient() throws IOException {
			if (writer != null) {
				writer.flush();
			}
			if (cache == null) {
				return;
			}
			byte[] data = cache.toByteArray();
			boolean doXslt = data.length > 0
					&& (forceStylesheet || containsHeader("XML-Encoded"));
			if (!doXslt) {
				if (data.length > 0) {
					orig.getOutputStream().write(data);
					orig.getOutputStream().flush();
				}
				return;
			}

			ServletContext sc = filterConfig.getServletContext();
			sc.log("Do transform");
			orig.setContentType("text/html; charset=UTF-8");
			try (InputStream is = new ByteArrayInputStream(data)) {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(is);

				TransformerFactory tf = TransformerFactory.newInstance();
				StreamSource stylesheet = new StreamSource(sc.getResourceAsStream(transformPath));
				Transformer transformer = tf.newTransformer(stylesheet);
				transformer.transform(new DOMSource(doc), new StreamResult(orig.getOutputStream()));
				orig.getOutputStream().flush();
			} catch (Exception ex) {
				sc.log("Error Transforming", ex);
			}
		}
	}
}
