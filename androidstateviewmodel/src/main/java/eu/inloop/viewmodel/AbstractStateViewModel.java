package eu.inloop.viewmodel;

public abstract class AbstractStateViewModel<T extends IView> extends AbstractViewModel<T> {
    protected ViewModelState state = new ViewModelState();

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
    private boolean coldStart;
    private boolean configChange;

    public void setColdStart(boolean coldStart) {
        this.coldStart = coldStart;
    }

    protected boolean isColdStart() {
        return coldStart;
    }

    public void setConfigChange(boolean configChange)
    {
        this.configChange = configChange;
    }

    protected boolean isConfigChange()
    {
        return configChange;
    }

    @Override
    public void onStart() {
        super.onStart();
        coldStart = (checkColdStart()) ? true : coldStart;
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
