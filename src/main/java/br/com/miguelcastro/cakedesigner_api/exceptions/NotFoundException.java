package br.com.miguelcastro.cakedesigner_api.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String errorMessage) {
        super(errorMessage.toString());
    }
}