package eu.inloop.viewmodel;

import android.os.Bundle;
import android.view.View;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;

public abstract class StateViewModelBaseFragment<T extends IView, R extends AbstractStateViewModel<T>> extends ViewModelBaseFragment<T, R> {

    private StateViewModelFragmentHelper<T> stateHelper;

    protected abstract T getModelView();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateHelper = new StateViewModelFragmentHelper<>(this, getViewModel());
        stateHelper.onFragmentCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(getModelView());
    }

    @Override
    public void onStop() {
        stateHelper.onFragmentStop();
        super.onStop();
    }
}
