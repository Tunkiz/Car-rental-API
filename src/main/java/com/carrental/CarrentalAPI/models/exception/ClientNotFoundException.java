package com.carrental.CarrentalAPI.models.exception;

import java.text.MessageFormat;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(final Long id){
        super(MessageFormat.format("Could not find client with id: {0}", id));
    }
}
