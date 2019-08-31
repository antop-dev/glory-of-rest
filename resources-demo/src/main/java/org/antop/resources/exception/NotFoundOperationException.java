package org.antop.resources.exception;

public class NotFoundOperationException extends RuntimeException {

    public NotFoundOperationException(String methodName) {
        super("non operation " + methodName);
    }
}
