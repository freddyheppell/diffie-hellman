package com.freddyheppell.dh.Message;

public class InvalidPacketException extends Exception {
    public InvalidPacketException(String expected, String actual) {
        super(String.format("Expected %s. Received:\n%s", expected, actual));
    }
}
