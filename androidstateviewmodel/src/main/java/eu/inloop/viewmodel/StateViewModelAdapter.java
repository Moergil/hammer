package eu.inloop.viewmodel;

/**
 * Adapter for {@link StateViewModelListener}
 *
 * @param <T> type of parameters for this adapter
 */
public class StateViewModelAdapter<T> implements StateViewModelListener<T> {
    @Override
    public void onGuiStateUpdate(T params) {
    }

    @Override
    public void onGuiUpdateEvents(T params) {
    }

    @Override
    public void onAppStateUpdate(T params) {
    }
}
