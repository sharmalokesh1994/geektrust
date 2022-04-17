package com.example.geektrust.exceptions;

public class NaviTestException extends RuntimeException {

    public NaviTestException( String message,Throwable cause ) {
        super(message, cause);
    }

    public NaviTestException( String message ) {
        super(message);
    }

    public NaviTestException( Throwable cause ) {
        super(cause);
    }

}
