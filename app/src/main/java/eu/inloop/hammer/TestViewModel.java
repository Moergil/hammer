package eu.inloop.hammer;

import android.os.Bundle;
import android.os.Handler;

import eu.inloop.viewmodel.AbstractStateViewModel;
import eu.inloop.viewmodel.ViewModelStateAdapter;
import eu.inloop.viewmodel.ViewModelStateListener;

public class TestViewModel extends AbstractStateViewModel<TestView> {

    private boolean loading;
    private String content;

    private long stopTimestamp;

    private ViewModelStateListener<String> contentLoadedListener;

    @Override
    public void onCreate(Bundle arguments, Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        contentLoadedListener = new ViewModelStateAdapter<String>() {
            @Override
            public void onGuiStateUpdate(String params) {
                TestViewModel.this.content = params;
            }

            @Override
            public void onGuiUpdateEvents(String params) {
                getView().onContentLoaded(params);
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();

        TestView view = getView();
        if (getState().isColdStart()) {
            view.setLoading();
            startContentLoad();
        } else if (loading) {
            view.setLoading();
        } else {
            view.setContent(content);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        stopTimestamp = System.currentTimeMillis();
    }

    @Override
    protected boolean checkColdStart() {
        return stopTimestamp + 10000 < System.currentTimeMillis();
    }

    private void startContentLoad() {
        loading = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading = false;
                fireListener(contentLoadedListener, "Hurray!");
            }
        }, 3000);
    }
}
