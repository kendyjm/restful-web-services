package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;
import java.util.StringJoiner;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public ExceptionResponse setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ExceptionResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public ExceptionResponse setDetails(String details) {
        this.details = details;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ExceptionResponse.class.getSimpleName() + "[", "]")
                .add("timestamp=" + timestamp)
                .add("message='" + message + "'")
                .add("details='" + details + "'")
                .toString();
    }
}
