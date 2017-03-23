package edu.asu.bsenetza.pox.foodmenu.bsenetzacli.menurequest;

public class MenuRequest implements IMenuRequest {
    private String menuRequest;

    @Override
    public String getMenuRequest() {
        return menuRequest;
    }
    
    @Override
    public void setMenuRequest(String menuRequest) {
        this.menuRequest = menuRequest;
    }
}



