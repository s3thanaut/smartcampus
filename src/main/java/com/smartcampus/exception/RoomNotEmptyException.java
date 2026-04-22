/*
    Name: Rathugamage Dharmarathne
    UoW_ID: W2151921
    IIT_ID: 20241775
*/

package com.smartcampus.exception;

/**
 * Thrown when a Room deletion is attempted but the room contains active sensors.
 * Maps to HTTP 409 Conflict.
 */
public class RoomNotEmptyException extends RuntimeException {
    public RoomNotEmptyException(String message) {
        super(message);
    }
}