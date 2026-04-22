/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.resources;

import com.smartcampus.model.Sensor;
import com.smartcampus.model.Room;
import com.smartcampus.exception.LinkedResourceNotFoundException;
import com.smartcampus.Database;
import com.smartcampus.GenericDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

@Path("/sensors")
public class SensorResource {
    
    private static final Logger LOG = Logger.getLogger(RoomResource.class.getName());

    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(Database.SENSORS);
    private GenericDAO<Room> roomDAO = new GenericDAO<>(Database.ROOMS);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensors(@QueryParam("type") String type) {
        List<Sensor> allSensors = sensorDAO.getAll();
        
        if(type == null || type.isEmpty()){
            return Response.ok(allSensors).build();
        }
        
        List<Sensor> filteredSensors = new ArrayList<>();
        for(Sensor s : allSensors){
            if(s.getType().equalsIgnoreCase(type))
                filteredSensors.add(s);
        }
        
        if(filteredSensors.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity(String.format("No %s sensors found", type)).build();
        }
        
        return Response.ok(filteredSensors).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerSensor(Sensor sensor) {
        // Validation: Verify roomId exists
        // If not, throw LinkedResourceNotFoundException();
        Room checkRoom = roomDAO.getById(sensor.getRoomId());
        if(checkRoom == null || checkRoom.getId().isEmpty()){
            throw new LinkedResourceNotFoundException("Room not found");
        }
        try {
            if(sensor.getType() == null || sensor.getRoomId() == null || sensor.getStatus() == null){
                throw new Exception("Missing Data: Requires Sensor type, roomID and Sensor Status");
            }
            sensorDAO.add(new Sensor(sensor.getType(), sensor.getRoomId(), sensor.getStatus()));
            return Response.status(Response.Status.CREATED).entity("Sensor registered successfully!").build();
        } catch (Exception e){
            LOG.info("Error while registering sensor: " + e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Sensor registration failed: " + e.getMessage()).build();
        }
    }

    @Path("/{sensorId}/readings")
    public SensorReadingResource getReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
}