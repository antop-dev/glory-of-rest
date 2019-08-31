package org.antop.resources.exception;

public class SlotNotAvailableException extends RuntimeException {

    public SlotNotAvailableException() {
        super("Slot not available");
    }

}
