package com.jasperstrijp.wolftracker.api;

import java.util.List;

public class DefaultWolfRepository implements WolfRepository {

    private final WolfContext wolfContext;

    public DefaultWolfRepository(WolfContext wolfContext) {
        this.wolfContext = wolfContext;
    }

    @Override
    public long saveWolf(Wolf wolf) {
        return wolfContext.saveWolf(wolf);
    }

    @Override
    public Wolf getWolf(long wolfId) {
        return wolfContext.getWolf(wolfId);
    }

    @Override
    public List<Wolf> getAllWolfs() {
        return wolfContext.getAllWolfs();
    }

    @Override
    public boolean removeWolf(long wolfId) {
        return wolfContext.removeWolf(wolfId);
    }

    @Override
    public boolean updateWolf(long wolfId, Wolf updatedWolf) {
        return wolfContext.updateWolf(wolfId, updatedWolf);
    }

    @Override
    public boolean updateWolfLocation(long wolfId, String location) {
        return wolfContext.updateWolfLocation(wolfId, location);
    }
}
