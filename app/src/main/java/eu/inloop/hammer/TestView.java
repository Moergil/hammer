package eu.inloop.hammer;

import eu.inloop.viewmodel.IView;

public interface TestView extends IView {
    void setLoading();

    void setContent(String content);

    void onContentLoaded(String content);
}
