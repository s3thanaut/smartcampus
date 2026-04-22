/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.exception.mapper;

import com.smartcampus.exception.LinkedResourceNotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DependencyExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    @Override
    public Response toResponse(LinkedResourceNotFoundException exception) {
        // Using 422 Unprocessable Entity as it is semantically accurate for missing references [cite: 157]
        return Response.status(422) 
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"error\": \"LinkedResourceNotFoundException\",\"message\": \"" + exception.getMessage() + "\"}")
                .build();
    }
}