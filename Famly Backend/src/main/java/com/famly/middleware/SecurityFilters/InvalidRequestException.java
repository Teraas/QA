package com.famly.middleware.SecurityFilters;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harish.kumar-mbp
 * createdOn 05/02/24
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String s) {
        super(s);
    }
}
