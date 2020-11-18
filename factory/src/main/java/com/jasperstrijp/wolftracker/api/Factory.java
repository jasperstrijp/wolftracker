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

    public PackLogic getPackLogic() {
        return new DefaultPackLogic(getPackRepository(), getWolfRepository());
    }

    public PackRepository getPackRepository() {
        return new DefaultPackRepository(getPackContext());
    }

    public PackContext getPackContext() {
        return new MySqlPackContext();
    }
}
