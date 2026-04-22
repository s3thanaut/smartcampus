/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.model;

import java.util.List;
import java.util.ArrayList;

public class Room implements BaseModel {

    private String id; // Unique identifier, e.g., "LIB-301"
    private String name; // Human-readable name, e.g., "Library Quiet Study"
    
    private int capacity; // Maximum occupancy for safety regulations
    private List<String> sensorIds = new ArrayList<>(); // Collection of IDs of sensors deployed in this room


// Constructors, getters, setters...
    
    public Room() {}
    
    public Room(String id, String name, int capacity){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getSensorIds() {
        return sensorIds;
    }
    
    public void addSensorId(String id){
        sensorIds.add(id);
    }
    
    public boolean noSensors(){
        return sensorIds.isEmpty();
    }
}
