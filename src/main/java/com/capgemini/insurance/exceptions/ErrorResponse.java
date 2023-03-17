package com.capgemini.insurance.exceptions;

import org.springframework.http.*;

import java.time.*;

public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;

    private String path;

    public ErrorResponse(int status, String error, String path, Instant timestamp) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.timestamp = timestamp;
    }
    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }



    public String getPath() {
        return path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}