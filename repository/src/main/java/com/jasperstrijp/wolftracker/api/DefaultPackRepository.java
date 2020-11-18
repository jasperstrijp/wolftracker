package com.jasperstrijp.wolftracker.api;

import java.util.List;

public class DefaultPackRepository implements PackRepository {

    private final PackContext packContext;

    public DefaultPackRepository(PackContext packContext) {
        this.packContext = packContext;
    }

    @Override
    public long savePack(Pack pack) {
        return packContext.savePack(pack);
    }

    @Override
    public boolean removeWolfFromPack(long packId, long wolfId) {
        return packContext.removeWolfFromPack(packId, wolfId);
    }

    @Override
    public boolean addWolfToPack(long packId, long wolfId) {
        return packContext.addWolfToPack(packId, wolfId);
    }

    @Override
    public List<Pack> getAllPacks() {
        return packContext.getAllPacks();
    }

    @Override
    public Pack getPackById(long packId) {
        return packContext.getPackById(packId);
    }
}
