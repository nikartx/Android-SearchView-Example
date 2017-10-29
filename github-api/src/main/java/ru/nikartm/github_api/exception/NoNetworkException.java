package ru.nikartm.github_api.exception;

/**
 * Custom network exception
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */
public class NoNetworkException extends RuntimeException {

    public NoNetworkException() {
    }

    public NoNetworkException(String message) {
        super(message);
    }
}
