package com.jasperstrijp.wolftracker.api;

import com.jasperstrijp.wolftracker.api.exceptions.InvalidWolfDataException;
import com.jasperstrijp.wolftracker.api.exceptions.WolfDoesNotExistException;

import java.security.InvalidParameterException;
import java.util.List;

public class DefaultWolfLogic implements WolfLogic {

    private static final String WOLF_ID_PARAMETER_LESS_THEN_ZERO_ERROR = "The wolfId parameter cannot be less then zero.";
    private static final String WOLF_DOES_NOT_EXIST = "No wolf has been found with the supplied id.";

    private final WolfRepository wolfRepository;

    public DefaultWolfLogic(WolfRepository wolfRepository) {
        this.wolfRepository = wolfRepository;
    }

    @Override
    public long saveWolf(Wolf wolf) throws InvalidWolfDataException {
        if (wolf.firstName.isBlank() || wolf.lastName.isBlank()){
            throw new InvalidWolfDataException("The first or lastname of the wolf is invalid");
        }

        if (wolf.gender == null){
            throw new InvalidWolfDataException("The gender of the wolf is invalid");
        }

        if (wolf.birthdate == null){
            throw new InvalidWolfDataException("The birthdate of the wolf is invalid");
        }

        return wolfRepository.saveWolf(wolf);
    }

    @Override
    public List<Wolf> getAllWolfs() throws WolfDoesNotExistException {
        List<Wolf> wolves = wolfRepository.getAllWolfs();

        if (wolves.isEmpty()){
            throw new WolfDoesNotExistException("No wolfs have been found");
        }

        return wolves;
    }

    @Override
    public Wolf getWolfById(long wolfId) throws WolfDoesNotExistException {
        if (wolfId < 0){
            throw new InvalidParameterException();
        }

        Wolf wolf = wolfRepository.getWolf(wolfId);

        if (wolf == null){
            throw new WolfDoesNotExistException(WOLF_DOES_NOT_EXIST);
        }

        return wolf;
    }

    @Override
    public boolean removeWolf(long wolfId) throws WolfDoesNotExistException {
        if (wolfId < 0){
            throw new InvalidParameterException(WOLF_ID_PARAMETER_LESS_THEN_ZERO_ERROR);
        }

        if (wolfRepository.getWolf(wolfId) == null){
            throw new WolfDoesNotExistException(WOLF_DOES_NOT_EXIST);
        }

        return wolfRepository.removeWolf(wolfId);
    }

    @Override
    public boolean updateWolf(long wolfId, Wolf updatedWolf) throws WolfDoesNotExistException {
        if (wolfId < 0){
            throw new InvalidParameterException(WOLF_ID_PARAMETER_LESS_THEN_ZERO_ERROR);
        }

        if (wolfRepository.getWolf(wolfId) == null){
            throw new WolfDoesNotExistException(WOLF_DOES_NOT_EXIST);
        }

        Wolf oldWolf = getWolfById(wolfId);
        return wolfRepository.updateWolf(wolfId, setNullValuesToOldValues(updatedWolf, oldWolf));
    }

    @Override
    public boolean updateWolfLocation(long wolfId, String location) throws InvalidWolfDataException, WolfDoesNotExistException {
        if (wolfId < 0){
            throw new InvalidParameterException(WOLF_ID_PARAMETER_LESS_THEN_ZERO_ERROR);
        }

        if (wolfRepository.getWolf(wolfId) == null){
            throw new WolfDoesNotExistException(WOLF_DOES_NOT_EXIST);
        }

        if (location.isBlank()){
            throw new InvalidWolfDataException("The new location is empty");
        }

        return wolfRepository.updateWolfLocation(wolfId, location);
    }

    private Wolf setNullValuesToOldValues(Wolf updated, Wolf old){
        if (updated.firstName == null || updated.firstName.isBlank()){
            updated.firstName = old.firstName;
        }

        if (updated.middleName == null || updated.middleName.isBlank()){
            updated.middleName = old.middleName;
        }

        if (updated.lastName == null || updated.lastName.isBlank()){
            updated.lastName = old.lastName;
        }

        if (updated.location == null || updated.location.isBlank()){
            updated.location = old.location;
        }

        if (updated.gender == null){
            updated.gender = old.gender;
        }

        if (updated.birthdate == null){
            updated.birthdate = old.birthdate;
        }

        return updated;
    }
}
