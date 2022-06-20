package com.vajro.shopify.product.exceptions;

import java.util.*;

public class ErrorDetails
{
    private Date timestamp;
    private String message;
    private String details;
    
    public ErrorDetails(Date timestamp,String message,String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
    
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getDetails() {
        return this.details;
    }
    
    @Override
    public String toString() {
        return "ErrorDetails [timestamp=" + this.timestamp + ", message=" + this.message + ", details=" + this.details + "]";
    }
}
