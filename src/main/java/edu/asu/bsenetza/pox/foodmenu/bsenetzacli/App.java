/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.bsenetza.pox.foodmenu.bsenetzacli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOG.info("Starting REST Client application");

        FoodMenuClient foodMenuClient = new FoodMenuClient("retrieve.xml");
        String response = foodMenuClient.postMenuRequest();
        LOG.info("\n" + response);

        foodMenuClient = new FoodMenuClient("new.xml");
        response = foodMenuClient.postMenuRequest();
        LOG.info("\n" + response);

        foodMenuClient = new FoodMenuClient("invalidretrieve.xml");
        response = foodMenuClient.postMenuRequest();
        LOG.info("\n" + response);

        foodMenuClient = new FoodMenuClient("invalidnew.xml");
        response = foodMenuClient.postMenuRequest();
        LOG.info("\n" + response);

        LOG.info("Ending REST Client application");

    }

}
