package com.jasperstrijp.wolftracker.api;

import java.util.List;

public class Pack {
    private long id;
    private String name;
    private List<Wolf> members;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wolf> getMembers() {
        return members;
    }

    public void setMembers(List<Wolf> members) {
        this.members = members;
    }
}
