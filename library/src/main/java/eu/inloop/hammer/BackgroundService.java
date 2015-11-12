package eu.inloop.hammer;

public interface BackgroundService {
    void start();
    void stop();
    boolean isRunning();
}
