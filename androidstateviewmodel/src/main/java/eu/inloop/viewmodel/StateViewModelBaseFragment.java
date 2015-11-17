package eu.inloop.viewmodel;

import android.os.Bundle;
import android.view.View;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;

/**
 * Extended base viewmodel fragment for use with the {@link AbstractStateViewModel}, providing state updates and helper methods.
 * @param <T> view
 * @param <R> viewmodel
 */
public abstract class StateViewModelBaseFragment<T extends IView, R extends AbstractStateViewModel<T>> extends ViewModelBaseFragment<T, R> {

    /**
     * Helper method to acquire view associated with the viewmodel.
     * @return associated view
     */
    protected abstract T getViewForViewModel();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(getViewForViewModel());
    }

    @Override
    public void onStop() {
        // Setting the configuration change state
        boolean configChange = getActivity().isChangingConfigurations();
        getViewModel().getState().setConfigChange(configChange);

        super.onStop();
    }
}
