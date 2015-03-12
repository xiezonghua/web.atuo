package com.huayu.web.server;

import java.io.File;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyServer {
	private final static int PORT_DEFAULT = 8088;

	private final static String CONTEXT_PATH_DEFAULT = "/";

	private final static Logger LOGGER = LoggerFactory
			.getLogger(JettyServer.class.getCanonicalName());

	public static void start() throws Exception {
		start(CONTEXT_PATH_DEFAULT, PORT_DEFAULT);
	}

	public static void start(final String contextPath, final int port)
			throws Exception {
		LOGGER.info("Jetty starting");
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
		server.setConnectors(new Connector[] { connector });

		/* ServletContextHandler context = new ServletContextHandler(); */
		WebAppContext context = new WebAppContext();
		context.setContextPath(contextPath);
		context.setDescriptor("WebContent/WEB-INF/web.xml");
		context.setResourceBase("WebContent");
		context.setContextPath("/webauto");
		context.setParentLoaderPriority(true);
		
		context.setExtraClasspath("WebContent/WEB-INF/classes/,WebContent/WEB-INF/lib/");
		File workTmp = new File("target/tmp");
		context.setTempDirectory(workTmp );
		

		HandlerCollection handlers = new HandlerCollection();
		handlers.setHandlers(new Handler[] { context, new DefaultHandler() });

		server.setHandler(handlers);
		server.start();
		server.join();

	}

	public static void main(String[] args) throws Exception {

		JettyServer.start();
	}

}
