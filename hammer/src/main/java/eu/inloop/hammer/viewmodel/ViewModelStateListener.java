package eu.inloop.hammer.viewmodel;

public class ViewModelStateListener<T> {

    protected void onGuiStateUpdate(T data) {}
    protected void onGuiUpdateEvents(T data) {}
    protected void onAppStateUpdate(T data) {}

    public void process(T data, ViewModelState viewModelState)
    {
        onAppStateUpdate(data);

        if (!viewModelState.isRemoved())
        {
            onGuiStateUpdate(data);

            if (viewModelState.isActive())
            {
                onGuiUpdateEvents(data);
            }
        }
    }
}
