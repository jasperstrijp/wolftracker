package com.jasperstrijp.wolftracker.api;

public class Factory {

    public WolfLogic getWolfLogic() {
        return new DefaultWolfLogic(getWolfRepository());
    }

    public WolfRepository getWolfRepository() {
        return new DefaultWolfRepository(getWolfContext());
    }

    public WolfContext getWolfContext() {
        return new MySqlWolfContext();
    }
}
