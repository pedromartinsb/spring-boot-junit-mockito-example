package br.com.pedrocampos.api.services.exceptions;

public class DataIntegrateViolationException extends RuntimeException {

    public DataIntegrateViolationException(String message) {
        super(message);
    }
}
