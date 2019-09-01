package org.antop.pox.exception;

public class NotFoundOperationException extends RuntimeException {

    public NotFoundOperationException(String methodName) {
        super("Not found operation " + methodName);
    }
}
