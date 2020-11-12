package com.flexion.converter.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {

    private String errorDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date responseTime;

    public ErrorResponse(){
        responseTime = new Date();
    }

    public ErrorResponse(String errorDescription){
        this.errorDescription = errorDescription;
        responseTime = new Date();
    }
}
