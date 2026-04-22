/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus;

import com.smartcampus.model.*;

import java.util.List;
import java.util.ArrayList;

public class Database {

    public static final List<Sensor> SENSORS = new ArrayList<>();
    public static final List<Room> ROOMS = new ArrayList<>();
    public static final List<SensorReading> READINGS = new ArrayList<>();

    static {
        ROOMS.add(new Room("LAB-1", "Laboratory 1", 50));
        ROOMS.add(new Room("LAB-2", "Laboratory 2", 75));
        ROOMS.add(new Room("AUDI", "Auditorium", 250));

        SENSORS.add(new Sensor("CO2", "LAB-1", "active"));
        SENSORS.add(new Sensor("TEMPERATURE", "LAB-1", "active"));
        SENSORS.add(new Sensor("HUMIDITY", "AUDI", "offline"));

        // Dummy Readings
        READINGS.add(new SensorReading("SEN-0001", 22.5));
        READINGS.add(new SensorReading("SEN-0001", 23.1));
        READINGS.add(new SensorReading("SEN-0001", 21.8));
        READINGS.add(new SensorReading("SEN-0001", 22.9));
        READINGS.add(new SensorReading("SEN-0001", 23.4));

        READINGS.add(new SensorReading("SEN-0002", 45.02));
        READINGS.add(new SensorReading("SEN-0002", 44.89));
        READINGS.add(new SensorReading("SEN-0002", 45.15));
        READINGS.add(new SensorReading("SEN-0002", 46.01));
        READINGS.add(new SensorReading("SEN-0002", 45.50));

        READINGS.add(new SensorReading("SEN-0003", 102.3));
        READINGS.add(new SensorReading("SEN-0003", 101.9));
        READINGS.add(new SensorReading("SEN-0003", 103.5));
        READINGS.add(new SensorReading("SEN-0003", 102.7));
        READINGS.add(new SensorReading("SEN-0003", 104.1));
    }

}
