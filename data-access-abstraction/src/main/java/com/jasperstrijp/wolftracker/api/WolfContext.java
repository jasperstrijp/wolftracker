package com.jasperstrijp.wolftracker.api;

import java.util.List;

public interface WolfContext {
    /**
     * Save a new wolf.
     * @param wolf The object to be saved.
     * @return The id of the newly added wolf.
     */
    long saveWolf(Wolf wolf);

    /**
     * Get a single wolf by Id.
     * @param wolfId the Id of the wolf to find.
     * @return The wolf if found, otherwise null.
     */
    Wolf getWolf(long wolfId);

//    List<Wolf> getAllWolfs();
//
//    boolean removeWolf(long wolfId);
//
//    boolean updateWolf(long wolfId, Wolf wolf);
//
//    boolean updateWolfLocation(long wolfId, String location);
}
