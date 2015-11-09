package eu.inloop.hammer;

public abstract class AbstractBackgroundService implements BackgroundService {
    private boolean running;

    @Override
    public void start() {
        if (!running)
        {
            running = true;
            onStarted();
        }
    }

    @Override
    public void stop() {
        if (running)
        {
            running = false;
            onStopped();
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    protected abstract void onStarted();
    protected abstract void onStopped();
}
