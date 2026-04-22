/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.model;

import com.smartcampus.Database;
import com.smartcampus.GenericDAO;
import com.smartcampus.model.Room;


public class Sensor implements BaseModel {
    
    private GenericDAO<Room> roomDAO = new GenericDAO<>(Database.ROOMS);
    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(Database.SENSORS);

    private String id; // Unique identifier, e.g., "TEMP-001"
    private String type; // Category, e.g., "Temperature", "Occupancy", "CO2"
    private String status; // Current state: "ACTIVE", "MAINTENANCE", or "OFFLINE"
    private double currentValue; // The most recent measurement recorded
    private String roomId; // Foreign key linking to the Room where the sensor is located.
    
    private int sensorNumber = 0;


// Constructors, getters, setters...
    public Sensor() {}
    
    public Sensor(String type, String roomId, String status){
        this.id = String.format("SEN-%04d", generateUUID());
        this.type = type;
        this.roomId = roomId;
        this.status = status.toUpperCase();
        this.currentValue = 0.00;
        
        setToRoom(roomId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    
    public void setToRoom(String roomId) {
        roomDAO.getById(roomId).addSensorId(id);
    }
    
    private int generateUUID(){
        sensorNumber = sensorDAO.getAll().size() + 1;
        return sensorNumber;
    }
}
