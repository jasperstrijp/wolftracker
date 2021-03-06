package com.jasperstrijp.wolftracker.api;

import com.jasperstrijp.wolftracker.api.exceptions.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultPackLogic implements PackLogic {

    private final PackRepository packRepository;
    private final WolfRepository wolfRepository;

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

        Pack pack = packRepository.getPackById(packId);
        pack.setMembers(removeNullMembers(pack.getMembers()));

        return pack;
    }

    private List<Wolf> removeNullMembers(List<Wolf> original){
        List<Wolf> wolves = new ArrayList<>(original);
        wolves.removeIf(Objects::isNull);
        return wolves;
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
            if (wolf == null){
                continue;
            }

            if (wolf.id == wolfId){
                return true;
            }
        }

        return false;
    }
}
