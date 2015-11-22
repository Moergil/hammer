package eu.inloop.viewmodel;

import android.support.annotation.CallSuper;

/**
 * Upgrade of {@link AbstractViewModel} class which adds state tracking mechanics and state based
 * callbacks. By tracking state changes of view (usually Fragment or Activity) it is possible to
 * better handle starting and stopping of the viewmodel and interaction with viewmodel from
 * asynchronous events can be simplified and standardized.
 * <p/>
 * The state of the viewmodel can be accessed anytime through {@link #getState()} method. It is
 * usually used during viewmodel start and stop callbacks, to finetune their behaviour, for example
 * not reloading the data and stopping background services when view restarted because of
 * configuration change. See {@link ViewModelState} for more details and usage.
 * <p/>
 * Every asynchronous callback should be routed through {@link StateViewModelListener} by using the
 * {@link #fireListener(StateViewModelListener, Object)} method. This mechanic provides callbacks
 * for common scenarios when asynchronous operation finishes: interaction with application
 * background, interaction with current viewmodel if present, and interaction with current view if
 * it is visible. See {@link StateViewModelListener} for more details and usage.
 * <p/>
 *
 * @param <T> view which can be associated with this viewmodel
 * @see ViewModelState
 * @see StateViewModelListener
 */
public abstract class AbstractStateViewModel<T extends IView> extends AbstractViewModel<T> {
    /**
     * Current state of this viewmodel.
     */
    private ViewModelState state = new ViewModelState();

    /**
     * Returns state object of this viewmodel.
     *
     * @return state object of this viewmodel
     */
    public ViewModelState getState() {
        return state;
    }

    /**
     * Lifecycle callback called when view is going to be started. Initial setup of view is usually
     * done in this method.
     * <p/>
     * It is possible to use {@link ViewModelState#isColdStart()} to detect, if viewmodel's current
     * state is probably obsolete and should be refreshed or to detect first start of the
     * viewmodel.
     * <p/>
     * In inherited classes the implementation have to call the super method before and other
     * operations.
     */
    @Override
    @CallSuper
    public void onStart() {
        super.onStart();

        if (checkColdStart()) {
            state.setColdStart(true);
        }

        state.setActive(true);
    }

    /**
     * Lifecycle callback called when view is going to be stopped. Cleanup of background tasks
     * related to current view can be done here.
     * <p/>
     * It is possible to use {@link ViewModelState#isConfigChange()} to detect, if the view is under
     * configuration change. In this case, it is not needed to stop background tasks, as the view
     * model will be almost instantly recreated and available.
     * <p/>
     * * In inherited classes the implementation have to call the super method before and other
     * operations.
     */
    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        state.setActive(false);
        state.setColdStart(false);
    }

    /**
     * Lifecycle callback called when viewmodel is removed from the view. This is called when
     * interaction with view ended. This callback is not called when concrete instance of view is
     * destroyed, as for example during configuration change, only when view doesn't exists anymore,
     * like when fragment is removed from layout or activity is finished.
     */
    @Override
    @CallSuper
    public void onModelRemoved() {
        super.onModelRemoved();
        state.setRemoved();
    }

    /**
     * Checks if viewmodel's current state is probably obsolete or not present. This is usually
     * after the view was not visible for some time or when the viewmodel was started for first
     * time.
     * <p/>
     * Implementations are free to add or replace the evaluation of cold start.
     * <p/>
     * Cold start will always be <code>true</code> during first start-stop cycle of viewmodel
     * when it is created for first time, even if this method returns <code>false</code>. Subsequent
     * start-stop cycles of viewmodel respects return value of this method.
     *
     * @return <code>true</code> if viewmodel is coldstarted, <code>false</code> otherwise
     */
    protected boolean checkColdStart() {
        return false;
    }

    /**
     * Fires {@link StateViewModelListener}. The callbacks of the listener are called according to
     * current state of the viewmodel.
     *
     * @param listener listener to fire
     * @param params   parameters for the listener
     * @param <T>      type of listnener parameters
     */
    protected <T> void fireListener(StateViewModelListener<T> listener, T params) {
        listener.onAppStateUpdate(params);

        if (!state.isRemoved()) {
            listener.onGuiStateUpdate(params);

            if (state.isActive()) {
                listener.onGuiUpdateEvents(params);
            }
        }
    }
}
