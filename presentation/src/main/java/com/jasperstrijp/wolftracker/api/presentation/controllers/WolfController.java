package com.jasperstrijp.wolftracker.api.presentation.controllers;

import com.jasperstrijp.wolftracker.api.Factory;
import com.jasperstrijp.wolftracker.api.Wolf;
import com.jasperstrijp.wolftracker.api.WolfLogic;
import com.jasperstrijp.wolftracker.api.exceptions.InvalidWolfDataException;
import com.jasperstrijp.wolftracker.api.exceptions.WolfDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wolf")
public class WolfController {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong";
    private static final String NOT_FOUND_MESSAGE = "The resource has not been found";
    private final WolfLogic wolfLogic;

    public WolfController() {
        this(new Factory());
    }

    public WolfController(Factory factory){
        this.wolfLogic = factory.getWolfLogic();
    }

    @PostMapping
    public ResponseEntity<Object> addWolf(@RequestBody Wolf wolf){
        try{
            long id = this.wolfLogic.saveWolf(wolf);
            return ResponseEntity.created(new URI("/wolf/" + id)).build();
        }
        catch (InvalidWolfDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllWolves(){
        try{
            List<Wolf> wolfList = this.wolfLogic.getAllWolfs();
            return ResponseEntity.ok(wolfList);
        }
        catch (WolfDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getWolfById(@PathVariable long id){
        try{
            Wolf wolf = this.wolfLogic.getWolfById(id);
            return ResponseEntity.ok(wolf);
        }
        catch (WolfDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWolf(@PathVariable long id){
        try{
            if (this.wolfLogic.removeWolf(id)){
                return ResponseEntity.noContent().build();
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong while deleting the resource, please try again later");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWolf(@PathVariable long id, @RequestBody Wolf wolf){
        try{
            if (this.wolfLogic.updateWolf(id, wolf)){
                return ResponseEntity.noContent().build();
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong while updating the resource, please try again later");
        }
        catch (WolfDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateWolfLocation(@PathVariable long id, @RequestBody String wolfLocation){
        try{
            if (this.wolfLogic.updateWolfLocation(id, wolfLocation)){
                return ResponseEntity.noContent().build();
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong while patching the resource, please try again later");
        }
        catch (InvalidWolfDataException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (WolfDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE);
        }
    }
}
