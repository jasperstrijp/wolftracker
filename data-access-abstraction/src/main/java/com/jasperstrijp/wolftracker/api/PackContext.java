package com.jasperstrijp.wolftracker.api;

import java.util.List;

public interface PackContext {
    long savePack(Pack pack);

    boolean addWolfToPack(long packId, long wolfId);

    List<Pack> getAllPacks();

    Pack getPackById(long packId);
}
