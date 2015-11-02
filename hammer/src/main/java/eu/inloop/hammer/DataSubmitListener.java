package eu.inloop.hammer;

public interface DataSubmitListener<R> {
    void onSuccess(R response);
    void onFail(Throwable t);
    void onViewActive();
    void onViewInactive();
}
