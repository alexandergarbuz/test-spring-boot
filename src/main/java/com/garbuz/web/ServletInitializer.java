package com.garbuz.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Servlet initializer is used to let your application start when it’s 
 * deployed as a traditional WAR file to an external servlet container 
 * (Tomcat, Jetty, WebSphere, etc.), instead of being launched 
 * via java -jar … with an embedded server.
 * 
 * SpringBootServletInitializer is triggered by the servlet container startup.
 * 
 * When you deploy your app as a WAR to an external container (Tomcat/Jetty/etc.), the container:
 *  - detects the web application (WAR)
 *  - runs the Servlet spec startup process
 *  - discovers Spring’s WebApplicationInitializer implementations (via ServletContainerInitializer + META-INF/services, using the container’s classpath scanning)
 *  - Spring’s startup code then ends up calling your class that extends SpringBootServletInitializer, invoking its configure(SpringApplicationBuilder) method to create the Boot application context.
 * 
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestSpringBootApplication.class);
	}

}
