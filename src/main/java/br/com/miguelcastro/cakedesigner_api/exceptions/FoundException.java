package br.com.miguelcastro.cakedesigner_api.exceptions;

public class FoundException extends RuntimeException {
    public FoundException(String errorMessage) {
        super(errorMessage.toString());
    }
}
