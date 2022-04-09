package com.lab2.emtbackend.model.exceptions;

import com.lab2.emtbackend.model.enums.ExceptionMessages;

public class CustomInvalidActionException extends RuntimeException {

    public CustomInvalidActionException() {
    }

    public CustomInvalidActionException(ExceptionMessages message) {
        if (message.equals(ExceptionMessages.NotAllowedRent)) {
            /* Still not implemented */
        }
    }
}
