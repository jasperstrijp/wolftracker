package com.jasperstrijp.wolftracker.api;

import java.util.List;

public interface WolfRepository {

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

    /**
     * Get all wolfs including data
     * @return A list of wolfs
     */
    List<Wolf> getAllWolfs();

    /**
     * Remove a wolf from the database
     * @param wolfId The id of the wolf to be removed
     * @return A boolean value indicating if the operation was successful
     */
    boolean removeWolf(long wolfId);

    /**
     * Update a wolf in the database
     * @param wolfId the Id of the wolf to update
     * @param updatedWolf A wolf object containing the new data, the id field is ignored
     * @return A boolean value indicating if the operation was successful
     */
    boolean updateWolf(long wolfId, Wolf updatedWolf);

    /**
     * Update the location of a wolf in the database
     * @param wolfId the id of the wolf to update
     * @param location the new location of the wolf
     * @return A boolean value indicating if the operation was successful
     */
    boolean updateWolfLocation(long wolfId, String location);
}
