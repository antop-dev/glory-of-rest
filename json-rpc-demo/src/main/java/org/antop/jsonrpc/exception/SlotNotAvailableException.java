package org.antop.jsonrpc.exception;

public class SlotNotAvailableException extends RuntimeException {

    public SlotNotAvailableException() {
        super("Slot not available");
    }

}
