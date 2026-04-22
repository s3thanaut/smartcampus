/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Custom filter for cross-cutting logging concerns.
 * Provides observability for every API request and response.
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    // Using the standard Java logging utility as required 
    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    /**
     * Intercepts every incoming request to log the Method and URI.
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String method = requestContext.getMethod();
        String uri = requestContext.getUriInfo().getRequestUri().toString();
        
        LOGGER.info(">>> [SMART CAMPUS] Request: " + method + " " + uri);
    }

    /**
     * Intercepts every outgoing response to log the HTTP Status Code.
     */
    @Override
    public void filter(ContainerRequestContext requestContext, 
                       ContainerResponseContext responseContext) throws IOException {
        int statusCode = responseContext.getStatus();
        
        LOGGER.info("<<< [SMART CAMPUS] Response Status: " + statusCode);
    }
}