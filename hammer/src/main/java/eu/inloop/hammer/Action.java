package eu.inloop.hammer;

public interface Action<T> {
    public static final Action EMPTY = new Action() {
        @Override
        public void onAction(Object data) {
        }
    };

    void onAction(T data);
}
