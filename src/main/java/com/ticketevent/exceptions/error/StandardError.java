package com.ticketevent.exceptions.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    private Long timestamp;
    private Integer statusCode;
    private String errorMessage;

 /*   public StandardError(Long timestamp, Integer statusCode,String errorMessage ) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }*/
}
