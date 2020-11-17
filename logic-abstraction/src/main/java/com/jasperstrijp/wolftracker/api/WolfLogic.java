package com.jasperstrijp.wolftracker.api;

import com.jasperstrijp.wolftracker.api.exceptions.InvalidWolfDataException;
import com.jasperstrijp.wolftracker.api.exceptions.WolfDoesNotExistException;

import java.util.List;

public interface WolfLogic {

    long saveWolf(Wolf wolf) throws InvalidWolfDataException;

    List<Wolf> getAllWolfs() throws WolfDoesNotExistException;

    Wolf getWolfById(long wolfId) throws WolfDoesNotExistException;

    boolean removeWolf(long wolfId) throws WolfDoesNotExistException;

    boolean updateWolf(long wolfId, Wolf updatedWolf) throws WolfDoesNotExistException;

    boolean updateWolfLocation(long wolfId, String location) throws InvalidWolfDataException, WolfDoesNotExistException;
}
