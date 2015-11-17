package eu.inloop.viewmodel;

/**
 * State based listener for processing asynchronous events in viewmodels.
 *
 * @see AbstractStateViewModel
 */
public interface StateViewModelListener<T> {
    /**
     * This callback is called first and every time. It is used for operations which are not
     * interacting with viewmodel directly. It should be used for interaction with application
     * background components, like various services or notifications.
     *
     * @param params listener parameters
     */
    void onAppStateUpdate(T params);

    /**
     * This callback is called after {@link #onAppStateUpdate(Object)} and only if associated
     * viewmodel is not removed. It should be used to change state of viewmodel, like setting newly
     * loaded data.
     *
     * @param params listener parameters
     */
    void onGuiStateUpdate(T params);

    /**
     * This callback is called after {@link #onGuiStateUpdate(Object)} and only if {@link
     * #onGuiStateUpdate(Object)} was called and associated viewmodel is active. It should be used
     * to notify view about viewmodel state changes made by{@link #onGuiStateUpdate(Object)}.
     *
     * @param params
     */
    void onGuiUpdateEvents(T params);
}
