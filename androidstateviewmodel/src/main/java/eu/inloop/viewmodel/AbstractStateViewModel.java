package eu.inloop.viewmodel;

import android.support.annotation.CallSuper;

public abstract class AbstractStateViewModel<T extends IView> extends AbstractViewModel<T> {
    private ViewModelState state = new ViewModelState();

    // TODO check and think about helper state callbacks, if they are allright
    /*
    USE CASES
    onStart
    - after onCreate, no data loaded, start everything
    - configuration change, data available and fresh
    - after return from another app / app part, data available, can be rotten

    onStop
    - configuration change, keep background tasks
    - another app / app part, usually stop tasks

    onModelRemoved
    - dispose everything
    - background tasks should be killed in onStop
     */

    public ViewModelState getState() {
        return state;
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();

        if (checkColdStart()) {
            state.setColdStart(true);
        }

        state.setActive(true);
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        state.setActive(false);
        state.setColdStart(false);
    }

    @Override
    @CallSuper
    public void onModelRemoved() {
        super.onModelRemoved();
        state.setRemoved();
    }

    protected boolean checkColdStart() {
        return false;
    }

    protected <T> void fireListener(ViewModelStateListener<T> listener, T params) {
        listener.onAppStateUpdate(params);

        if (!state.isRemoved()) {
            listener.onGuiStateUpdate(params);

            if (state.isActive()) {
                listener.onGuiUpdateEvents(params);
            }
        }
    }
}
