package eu.inloop.hammer.async;

public interface TaskHandle {
    public static TaskHandle EMPTY = new TaskHandle() {
        @Override
        public void cancel() {
        }
    };

    void cancel();
}
