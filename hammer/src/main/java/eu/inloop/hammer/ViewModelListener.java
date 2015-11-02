package eu.inloop.hammer;

public class ViewModelListener<T> {

    protected void guiStateUpdate(T data) {}
    protected void guiUpdateEvents(T data) {}
    protected void appStateUpdate(T data) {}

    public void process(T data, ViewModelState viewModelState)
    {
        if (viewModelState.isRemoved())
        {
            appStateUpdate(data);
        }
        else
        {
            guiStateUpdate(data);

            if (viewModelState.isActive())
            {
                guiUpdateEvents(data);
            }
        }
    }
}
