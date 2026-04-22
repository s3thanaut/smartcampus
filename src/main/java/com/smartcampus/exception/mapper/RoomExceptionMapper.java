/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.exception.mapper;

import com.smartcampus.exception.RoomNotEmptyException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {
    @Override
    public Response toResponse(RoomNotEmptyException exception) {
        return Response.status(Response.Status.CONFLICT) // HTTP 409
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"error\": \"RoomNotEmptyException\",\"message\": \"" + exception.getMessage() + "\"}")
                .build();
    }
}