package eu.inloop.hammer.async;

public class PlainTaskHandle implements TaskHandle {
    private boolean cancelled;

    @Override
    public void cancel() {
        this.cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
