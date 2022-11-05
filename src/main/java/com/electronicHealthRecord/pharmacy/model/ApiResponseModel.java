package com.electronicHealthRecord.pharmacy.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApiResponseModel {
    private int statusCode ;
    private String message ;
    private Object data ;
}

