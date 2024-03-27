package com.example.crud.web.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
@Builder
public class CustomErrorMessage {
    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;
    private String exceptionName;
}
