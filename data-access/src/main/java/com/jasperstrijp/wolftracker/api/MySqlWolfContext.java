package com.jasperstrijp.wolftracker.api;

public class MySqlWolfContext implements WolfContext {
    @Override
    public long saveWolf(Wolf wolf) {
        return 0;
    }

    @Override
    public Wolf getWolf(long wolfId) {
        return null;
    }
}
