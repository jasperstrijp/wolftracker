package com.jasperstrijp.wolftracker.api;

import java.util.List;

public interface PackRepository {
    long savePack(Pack pack);

    boolean addWolfToPack(long packId, long wolfId);

    List<Pack> getAllPacks();

    Pack getPackById(long packId);
}
