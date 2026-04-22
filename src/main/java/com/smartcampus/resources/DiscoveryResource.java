/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class DiscoveryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscovery() {
        Map<String, Object> metadata = new HashMap<>();
        Map<String, String> links = new HashMap<>();
        
        links.put("rooms", "/api/v1/rooms");
        links.put("room by ID", "/api/v1/rooms/{ID}");
        links.put("sensors", "/api/v1/sensors");
        links.put("sensorReadings", "/api/v1/{id}/readings");
        metadata.put("_links", links);
        
        metadata.put("version", "1.0.0");
        metadata.put("contact", "admin@smartcampus.edu");
        

        return Response.ok(metadata).build();
    }
}