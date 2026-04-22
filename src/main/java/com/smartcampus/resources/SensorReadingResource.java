/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.resources;

import com.smartcampus.model.SensorReading;
import com.smartcampus.model.Sensor;
import com.smartcampus.GenericDAO;
import com.smartcampus.Database;
import com.smartcampus.exception.LinkedResourceNotFoundException;
import com.smartcampus.exception.SensorUnavailableException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import java.util.List;
import java.util.ArrayList;

public class SensorReadingResource {
    
    GenericDAO<SensorReading> readingDAO = new GenericDAO<>(Database.READINGS);
    GenericDAO<Sensor> sensorDAO = new GenericDAO<>(Database.SENSORS);
    
    private static final Logger LOG = Logger.getLogger(RoomResource.class.getName());    
    
    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistory() {
        // Fetch history for this.sensorId 
        List<SensorReading> readingsList = new ArrayList<>();
        for(SensorReading reading : readingDAO.getAll()){
            if(reading.getSensorId().equals(sensorId)){
                readingsList.add(reading);
            }
        }
        return Response.ok(readingsList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {
        // Implementation: Append reading 
        // Side Effect: Update parent Sensor's currentValue
        Sensor checkSensor = sensorDAO.getById(sensorId);
        
        if(checkSensor == null){
            throw new LinkedResourceNotFoundException("Sensor not found");
        }
        try {            
            if(checkSensor.getStatus().equals("MAINTENANCE") || checkSensor.getStatus().equals("OFFLINE")){
                throw new SensorUnavailableException("Sensor is offline or under maintenance");
            }
            readingDAO.add(new SensorReading(sensorId, reading.getValue()));
            return Response.status(Response.Status.CREATED).entity("Reading logged successfully!").build();
        } catch (Exception e){
            LOG.info("Error while registering sensor: " + e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Reading logging failed: " + e.getMessage()).build();
        }
    }
}