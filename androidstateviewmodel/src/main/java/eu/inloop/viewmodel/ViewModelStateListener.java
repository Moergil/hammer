package eu.inloop.viewmodel;

public interface ViewModelStateListener<T> {
    void onGuiStateUpdate(T params);

    void onGuiUpdateEvents(T params);

    void onAppStateUpdate(T params);
}
