package com.jasperstrijp.wolftracker.api;

import com.jasperstrijp.wolftracker.api.exceptions.*;

import java.util.List;

public interface PackLogic {
    long savePack(Pack pack) throws InvalidPackDataException;

    boolean removeWolfFromPack(long packId, long wolfId) throws PackDoesNotExistException, WolfDoesNotExistException, WolfIsNotInPackException;

    boolean addWolfToPack(long packId, long wolfId) throws PackDoesNotExistException, WolfDoesNotExistException, WolfIsAlreadyInPackException;

    List<Pack> getAllPacks() throws PackDoesNotExistException;

    Pack getPackById(long packId) throws PackDoesNotExistException;
}
