/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.resources;

import com.smartcampus.model.Room;
import com.smartcampus.*;
import com.smartcampus.exception.RoomNotEmptyException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/rooms")
public class RoomResource {

    private GenericDAO<Room> roomDAO = new GenericDAO<>(Database.ROOMS);

    private static final Logger LOG = Logger.getLogger(RoomResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms() {
        LOG.info("Getting all room objects");
        return roomDAO.getAll();
    }

    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoomById(@PathParam("roomId") String roomId) {
        Room room = roomDAO.getById(roomId);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room with ID " + roomId + " is not found")
                    .build();
        }

        return Response.ok(room).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room) {
        try {
            roomDAO.add(new Room(room.getId(), room.getName(), room.getCapacity()));
            return Response.status(Response.Status.CREATED).entity("Room created successfully").build();
        } catch (Exception e){
            LOG.info("Error while creating room: " + e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Error while adding new room").build();
        }
    }

    @DELETE
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        // Business Logic: Check if room has active sensors
        // If sensors exist, throw new RoomNotEmptyException();
        
        Room room = roomDAO.getById(roomId);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room with ID " + roomId + " is not found")
                    .build();
        }else if(room.getSensorIds() != null && !room.getSensorIds().isEmpty()){
            throw new RoomNotEmptyException("Room contains sensors. Cannot remove");
        }
        
        roomDAO.delete(roomId);
        return Response.ok().entity("Room removed successfully!").build();
    }
}
