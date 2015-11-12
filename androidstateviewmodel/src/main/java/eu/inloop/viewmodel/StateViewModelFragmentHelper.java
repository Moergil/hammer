package eu.inloop.viewmodel;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class StateViewModelFragmentHelper<T extends IView> {
    private Fragment fragment;
    private AbstractStateViewModel<T> viewmodel;

    public StateViewModelFragmentHelper(Fragment fragment, AbstractStateViewModel<T> viewmodel) {
        this.fragment = fragment;
        this.viewmodel = viewmodel;
    }

    public void onFragmentCreated(Bundle savedInstanceState)
    {
        viewmodel.setColdStart(savedInstanceState == null);
    }

    public void onFragmentStop()
    {
        viewmodel.setConfigChange(fragment.getActivity().isChangingConfigurations());
        viewmodel.setColdStart(false);
    }
}
