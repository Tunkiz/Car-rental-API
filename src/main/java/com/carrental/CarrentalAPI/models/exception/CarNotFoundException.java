package com.carrental.CarrentalAPI.models.exception;

import java.text.MessageFormat;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(final Long id){
        super(MessageFormat.format("Could not find car with id: {0}", id));
    }
}
