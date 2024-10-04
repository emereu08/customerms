package com.nttdata.customerms.business.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternoExcepcion extends RuntimeException {
    public ErrorInternoExcepcion(String message) {
        super(message);
    }
}
