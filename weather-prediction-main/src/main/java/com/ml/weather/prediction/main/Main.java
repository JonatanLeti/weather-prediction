package com.ml.weather.prediction.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {

    private static final int DEFAULT_PORT = 9290;
    private static final String INDEX_PAGE = "index.html";

    public Main() {
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(DEFAULT_PORT);
        ResourceHandler resource_handler = new ResourceHandler();

        final Injector injector = Guice.createInjector(new MainModule(), new RouterModule());

        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{INDEX_PAGE});

        ServletContextHandler sch = new ServletContextHandler(server, "/");

        sch.addEventListener(new GuiceServletContextListener() {
            @Override
            protected Injector getInjector() {
                return injector;
            }
        });

        sch.addFilter(GuiceFilter.class, "/*", null);
        sch.addServlet(DefaultServlet.class, "/");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, sch});
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
