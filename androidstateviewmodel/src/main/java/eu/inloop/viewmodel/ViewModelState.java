package eu.inloop.viewmodel;

public class ViewModelState {
    private boolean removed;
    private boolean active;
    private boolean coldStart = true;
    private boolean configChange;

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setRemoved() {
        this.removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public boolean isColdStart() {
        return coldStart;
    }

    public void setColdStart(boolean coldStart) {
        this.coldStart = coldStart;
    }

    public boolean isConfigChange() {
        return configChange;
    }

    public void setConfigChange(boolean configChange) {
        this.configChange = configChange;
    }
}
