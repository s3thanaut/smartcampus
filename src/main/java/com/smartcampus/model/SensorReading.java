/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.model;

import com.smartcampus.GenericDAO;
import com.smartcampus.Database;

public class SensorReading implements BaseModel {
    
    private int readingNumber = 0;

    private String id; // Unique reading event ID (UUID recommended)
    private String sensorId; // The ID of the sensor reading was made
    private long timestamp; // Epoch time (ms) when the reading was captured
    private double value; // The actual metric value recorded by the hardware

    GenericDAO<SensorReading> readingDAO = new GenericDAO<>(Database.READINGS);
    GenericDAO<Sensor> sensorDAO = new GenericDAO<>(Database.SENSORS);

// Constructors, getters, setters...
    public SensorReading() {}
    
    public SensorReading(String sensorId, double value) {
        this.id = String.format("READ-%06d", generateUUID());
        this.sensorId = sensorId;
        this.timestamp = System.currentTimeMillis();
        this.value = value;
        
        updateSensorValue(sensorId, value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
    
    private int generateUUID(){
        readingNumber = readingDAO.getAll().size() + 1;
        return readingNumber;
    }
    
    private void updateSensorValue(String sensorId, double value){
        Sensor sensor = sensorDAO.getById(sensorId);
        sensor.setCurrentValue(value);
    }
}
