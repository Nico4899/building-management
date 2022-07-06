package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.exception;

public class InvalidArgumentsException extends RuntimeException {

    private static final String INVALID_ARGUMENTS_EXCEPTION_MESSAGE = "Arguments: %s are invalid for %s request!";

    public InvalidArgumentsException() {
        super(INVALID_ARGUMENTS_EXCEPTION_MESSAGE);
    }
}
