package eu.inloop.hammer.viewmodel;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;

public class AppViewModel<T extends IView> extends AbstractViewModel<T> {
    protected ViewModelState state = new ViewModelState();

    private boolean paused, restored;

    public void setPaused()
    {
        this.paused = true;
    }

    public void setRestored()
    {
        this.restored = true;
    }

    protected boolean isPaused()
    {
        return paused;
    }
    protected boolean isRestored() { return restored; }

    @Override
    public void onStart() {
        paused = false;
        super.onStart();
        restored = false;
        state.setActive(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        state.setActive(false);
    }

    @Override
    public void onModelRemoved() {
        super.onModelRemoved();
        state.setRemoved();
    }
}
