package eu.inloop.hammer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.inloop.view.ContentSwitcher;
import eu.inloop.viewmodel.StateViewModelBaseFragment;

public class TestFragment extends StateViewModelBaseFragment<TestView, TestViewModel> implements TestView {

    @Bind(R.id.progress)
    protected ProgressBar loadingView;

    @Bind(R.id.txt_content)
    protected TextView contentView;

    private ContentSwitcher contentSwitcher = new ContentSwitcher(300);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, getView());

        contentSwitcher.prepare(loadingView, contentView);
    }

    @Override
    protected TestView getModelView() {
        return this;
    }

    @Override
    public void setLoading() {
        contentSwitcher.setTo(loadingView);
    }

    @Override
    public void setContent(String content) {
        contentView.setText(content);
        contentSwitcher.setTo(contentView);
    }

    @Override
    public void onContentLoaded(String content) {
        contentView.setText(content);
        contentSwitcher.changeTo(contentView);
    }

    @Override
    public Class<TestViewModel> getViewModelClass() {
        return TestViewModel.class;
    }
}
