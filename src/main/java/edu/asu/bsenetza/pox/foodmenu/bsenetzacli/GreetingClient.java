/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.bsenetza.pox.foodmenu.bsenetzacli;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jersey REST client generated for REST resource:GreetingResource
 * [myresource]<br>
 * USAGE:
 * <pre>
 *        GreetingClient client = new GreetingClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author calliss
 */
public class GreetingClient {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingClient.class);

    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FoodItems/webapi";

    public GreetingClient() {
        LOG.info("Creating a Greeting REST Client");
        
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("inventory");
    }

    public void postTextGreeting(String greetingMessage) throws UniformInterfaceException {
        LOG.info("Send the greeting message \"" + greetingMessage + "\" to the Greeting REST Server");
        webResource.type(MediaType.TEXT_PLAIN).post(greetingMessage);
    }

    public String getHtmlGreeting() throws UniformInterfaceException  {
        LOG.info("Retrieving the greeting message from to the Greeting REST Server");
        
        WebResource resource = webResource;
        String retrievedGreeting = resource.accept(MediaType.TEXT_HTML).get(String.class);
        LOG.info("Retrieved the greeting " + retrievedGreeting);
        
        return retrievedGreeting;
    }

    public void close() {
        client.destroy();
    }
    
}
