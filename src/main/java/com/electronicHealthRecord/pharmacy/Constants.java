package com.electronicHealthRecord.pharmacy;

import lombok.Data;

@Data
public class Constants {
    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 503;
    public static final String INVENTORY_GET_SUCCESS = "Items found";
}
