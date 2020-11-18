package com.jasperstrijp.wolftracker.api;

import com.jasperstrijp.wolftracker.api.exceptions.*;

import java.security.InvalidParameterException;
import java.util.List;

public class DefaultPackLogic implements PackLogic {

    private PackRepository packRepository;
    private WolfRepository wolfRepository;

    private static final String PACK_ID_PARAMETER_LESS_THEN_ZERO_ERROR = "The PackId parameter cannot be less then zero.";
    private static final String PACK_DOES_NOT_EXIST_ERROR = "No wolf has been found with the supplied id.";
    private static final String WOLF_DOES_NOT_EXIST_ERROR = "No wolf has been found with the supplied id.";


    public DefaultPackLogic(PackRepository packRepository, WolfRepository wolfRepository) {
        this.packRepository = packRepository;
        this.wolfRepository = wolfRepository;
    }

    @Override
    public long savePack(Pack pack) throws InvalidPackDataException {
        if (pack.getName() == null || pack.getName().isBlank()){
            throw new InvalidPackDataException("The name of the pack is invalid");
        }

        pack.setMembers(null);

        return packRepository.savePack(pack);
    }

    @Override
    public boolean removeWolfFromPack(long packId, long wolfId) throws PackDoesNotExistException, WolfDoesNotExistException, WolfIsNotInPackException {
        if (packId < 0 || wolfId < 0){
            throw new InvalidParameterException("The packId or wolfId parameter cannot be less then zero.");
        }

        if (!isWolfInPack(packId, wolfId)){
            throw new WolfIsNotInPackException("The wolf is not in the pack");
        }

        return packRepository.removeWolfFromPack(packId, wolfId);
    }

    @Override
    public boolean addWolfToPack(long packId, long wolfId) throws PackDoesNotExistException, WolfDoesNotExistException, WolfIsAlreadyInPackException {
        if (packId < 0 || wolfId < 0){
            throw new InvalidParameterException("The packId or wolfId parameter cannot be less then zero.");
        }

        if (isWolfInPack(packId, wolfId)){
            throw new WolfIsAlreadyInPackException("The wolf is already in the pack");
        }

        return packRepository.addWolfToPack(packId, wolfId);
    }

    @Override
    public List<Pack> getAllPacks() throws PackDoesNotExistException {
        List<Pack> packs = packRepository.getAllPacks();

        if (packs.isEmpty()){
            throw new PackDoesNotExistException(PACK_DOES_NOT_EXIST_ERROR);
        }

        return packs;
    }

    @Override
    public Pack getPackById(long packId) throws PackDoesNotExistException {
        if (packId < 0){
            throw new InvalidParameterException("The packId or wolfId parameter cannot be less then zero.");
        }

        if (packRepository.getPackById(packId) == null){
            throw new PackDoesNotExistException(PACK_DOES_NOT_EXIST_ERROR);
        }

        return packRepository.getPackById(packId);
    }

    private boolean isWolfInPack(long packId, long wolfId) throws PackDoesNotExistException, WolfDoesNotExistException {
        if (packRepository.getPackById(packId) == null){
            throw new PackDoesNotExistException(PACK_DOES_NOT_EXIST_ERROR);
        }

        if (wolfRepository.getWolf(wolfId) == null){
            throw new WolfDoesNotExistException(WOLF_DOES_NOT_EXIST_ERROR);
        }

        Pack pack = getPackById(packId);

        for (Wolf wolf: pack.getMembers()) {
            return wolf.id == wolfId;
        }

        return false;
    }
}
