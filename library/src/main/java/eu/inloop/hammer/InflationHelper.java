package eu.inloop.hammer;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class InflationHelper {
    public static View inflateFragment(LayoutInflater inflater, int layoutId, ViewGroup container, Fragment fragment)
    {
        View layout = inflater.inflate(layoutId, container, false);

        ButterKnife.bind(fragment, layout);

        return layout;
    }
}
