package com.jasperstrijp.wolftracker.api.presentation.Dto;

import com.jasperstrijp.wolftracker.api.Pack;

public class PackDto {
    private long wolfId;
    private String name;

    public PackDto() {
    }

    public long getWolfId() {
        return wolfId;
    }

    public void setWolfId(long wolfId) {
        this.wolfId = wolfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pack convertFromDto(){
        Pack pack = new Pack();
        pack.setName(this.getName());

        return pack;
    }
}
