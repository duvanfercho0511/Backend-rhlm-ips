package com.rhlmips.rhlmips.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -164750102277482475L;
    public DataNotFoundException() {
        super("No se encuentra el registro con el identificador proporcionado.");
    }
}

