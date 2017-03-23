/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.bsenetza.pox.foodmenu.bsenetzacli;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import edu.asu.bsenetza.pox.foodmenu.bsenetzacli.menurequest.MenuRequest;
import edu.asu.bsenetza.pox.foodmenu.bsenetzacli.menurequest.MenuRequestFactory;
import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FoodMenuClient {

    private static final Logger LOG = LoggerFactory.getLogger(FoodMenuClient.class);

    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FoodItems/webapi";

    MenuRequestFactory menuRequestFactory = new MenuRequestFactory();
    MenuRequest menuRequest;

    public FoodMenuClient() {
        configClient();
        setWebResourcePath();
        try {
            menuRequest = menuRequestFactory.createMenuRequest(readMenuRequest());
            LOG.debug(menuRequest.getMenuRequest());
        } catch (TransformerException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
    }

    public FoodMenuClient(String fileName) {
        configClient();
        setWebResourcePath();
        try {
            menuRequest = menuRequestFactory.createMenuRequest(readMenuRequest(fileName));
            LOG.debug(menuRequest.getMenuRequest());
        } catch (TransformerException ex) {
            LOG.error(ex.getLocalizedMessage());
        }
    }

    private void configClient() {
        client = Client.create(new DefaultClientConfig());
    }

    private void setWebResourcePath() {
        webResource = client.resource(BASE_URI).path("inventory");

    }

    public String postMenuRequest() {
        ClientResponse response = webResource.type(MediaType.APPLICATION_XML).accept(MediaType.TEXT_XML).post(ClientResponse.class, menuRequest.getMenuRequest());
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        LOG.debug("Output from Server: \n");
        String output = response.getEntity(String.class);
        LOG.debug(output);
        return output;
    }

    public void close() {
        client.destroy();
    }

    private Document readMenuRequest() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File menu = new File(classLoader.getResource("menu.xml").getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(menu);
            return doc;
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            LOG.error(ex.getLocalizedMessage());
            return null;
        }
    }

    private Document readMenuRequest(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File menu = new File(classLoader.getResource(fileName).getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(menu);
            return doc;
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            LOG.error(ex.getLocalizedMessage());
            return null;
        }
    }
}
