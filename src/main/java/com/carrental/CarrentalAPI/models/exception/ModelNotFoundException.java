package com.carrental.CarrentalAPI.models.exception;

import java.text.MessageFormat;

public class ModelNotFoundException extends RuntimeException{
    public ModelNotFoundException(final Long id){
        super(MessageFormat.format("Could not find model with id: {0}", id));
    }
}
