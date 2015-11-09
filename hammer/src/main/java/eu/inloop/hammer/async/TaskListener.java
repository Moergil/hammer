package eu.inloop.hammer.async;

public interface TaskListener<R, F> {
    void onSuccess(R result);
    void onFail(F fail);
}
