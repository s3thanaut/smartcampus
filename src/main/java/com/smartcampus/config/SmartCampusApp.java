/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.config;

import com.smartcampus.resources.*;
import com.smartcampus.exception.mapper.*;
import com.smartcampus.filter.LoggingFilter;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
public class SmartCampusApp extends Application {
    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> classes = new HashSet<>();
        classes.add(DiscoveryResource.class);
        classes.add(RoomResource.class);
        classes.add(SensorResource.class);
        
        classes.add(RoomExceptionMapper.class);
        classes.add(GlobalExceptionMapper.class);
        classes.add(SensorStatusMapper.class);
        classes.add(DependencyExceptionMapper.class);
        
        classes.add(LoggingFilter.class);
        
        return classes;
    }
}
