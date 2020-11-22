package com.jasperstrijp.wolftracker.api.presentation.controllers;

import com.jasperstrijp.wolftracker.api.Factory;
import com.jasperstrijp.wolftracker.api.Pack;
import com.jasperstrijp.wolftracker.api.PackLogic;
import com.jasperstrijp.wolftracker.api.exceptions.InvalidPackDataException;
import com.jasperstrijp.wolftracker.api.exceptions.PackDoesNotExistException;
import com.jasperstrijp.wolftracker.api.exceptions.WolfDoesNotExistException;
import com.jasperstrijp.wolftracker.api.exceptions.WolfIsAlreadyInPackException;
import com.jasperstrijp.wolftracker.api.presentation.Dto.PackDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/pack")
public class PackController {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong";
    private final PackLogic packLogic;

    public PackController() {
        this(new Factory());
    }

    public PackController(Factory factory) {
        this.packLogic = factory.getPackLogic();
    }

    @PostMapping
    public ResponseEntity<Object> addPack(@RequestBody PackDto dto) {
        try {
            long packId = packLogic.savePack(dto.convertFromDto());
            packLogic.addWolfToPack(packId, dto.getWolfId());

            return ResponseEntity.created(new URI("/pack/" + packId)).build();
        }
        catch (InvalidPackDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (WolfDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wolf does not exist.");
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllPacks() {
        try {
            List<Pack> packList = packLogic.getAllPacks();
            return ResponseEntity.ok(packList);
        }
        catch (PackDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No packs have been found.");
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPackById(@PathVariable long id) {
        try {
            Pack pack = packLogic.getPackById(id);
            return ResponseEntity.ok(pack);
        }
        catch (PackDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @PostMapping("/{packId}/members/")
    public ResponseEntity<Object> addWolfToPack(@PathVariable long packId, @RequestBody long wolfId) {
        try {
            if (packLogic.addWolfToPack(packId, wolfId)) {
                return ResponseEntity.ok().build();
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
        }
        catch (WolfIsAlreadyInPackException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (WolfDoesNotExistException | PackDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }
}
