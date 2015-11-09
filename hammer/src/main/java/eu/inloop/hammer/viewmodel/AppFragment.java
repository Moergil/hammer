package eu.inloop.hammer.viewmodel;

import android.os.Bundle;
import android.view.View;

import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;

public abstract class AppFragment<T extends IView, R extends AppViewModel<T>> extends ViewModelBaseFragment<T, R> {
    protected abstract T onSetViewModel();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setModelView(onSetViewModel());

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        boolean restored = !getActivity().isChangingConfigurations();
        if (restored)
        {
            getViewModel().setRestored();
        }

        super.onStart();
    }

    @Override
    public void onStop() {
        boolean changing = getActivity().isChangingConfigurations();
        if (!changing) {
            getViewModel().setPaused();
        }

        super.onStop();
    }
}
