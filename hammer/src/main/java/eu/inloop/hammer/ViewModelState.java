package eu.inloop.hammer;

import java.util.LinkedList;
import java.util.List;

public class ViewModelState {
    private boolean removed;
    private boolean active;

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved() {
        this.removed = true;
    }
}
