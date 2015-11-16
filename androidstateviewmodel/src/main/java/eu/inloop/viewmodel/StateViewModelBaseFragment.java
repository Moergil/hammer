package eu.inloop.viewmodel;

import android.os.Bundle;
import android.view.View;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;

public abstract class StateViewModelBaseFragment<T extends IView, R extends AbstractStateViewModel<T>> extends ViewModelBaseFragment<T, R> {

    protected abstract T getViewForViewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(getViewForViewModel());
    }

    @Override
    public void onStop() {
        boolean configChange = getActivity().isChangingConfigurations();
        getViewModel().getState().setConfigChange(configChange);

        super.onStop();
    }
}
