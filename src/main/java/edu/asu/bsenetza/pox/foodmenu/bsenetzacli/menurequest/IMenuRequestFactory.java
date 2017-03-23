package edu.asu.bsenetza.pox.foodmenu.bsenetzacli.menurequest;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;

public interface IMenuRequestFactory {

    public MenuRequest createMenuRequest(Document document) throws TransformerException;

}
