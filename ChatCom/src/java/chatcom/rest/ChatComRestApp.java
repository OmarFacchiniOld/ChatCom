/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatcom.rest;

/**
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
import java.io.IOException;
import java.net.URI;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Uberti Davide <ubertidavide@gmail.com>
 */
public class ChatComRestApp{
   private static final URI BASE_URI = URI.create("http://localhost:8888/api/");
    public static final String ROOT_PATH = "";
    
    /**
     * @param args the command line arguments
     */
    
     private static ResourceConfig createResourcConfig() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(ChatgroupResource.class);
        resourceConfig.register(InstanceResource.class);
        resourceConfig.register(MessageResource.class);
        resourceConfig.register(UserResource.class);
        return resourceConfig;
    }    

    public static void main(String[] args) {
        try {
            System.out.println("\"Todo\" Jersey Rest Web Service ChatComApp");

            Logger l = Logger.getLogger("org.glassfish.grizzly.http.server.HttpHandler");
            l.setLevel(Level.FINE);
            l.setUseParentHandlers(false);
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.ALL);
            l.addHandler(ch);
            
            HttpServer server = createServerInstance();
            server.start();

            System.out.println(String.format("Application started.\nTry out %s%s\nStop the application using CTRL+C",
                    BASE_URI, ROOT_PATH));
            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ChatComRestApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static HttpServer createServerInstance() {
        ResourceConfig resourceConfig = createResourcConfig();
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.shutdownNow();
            }
        }));
        return server;
    }
    
}

