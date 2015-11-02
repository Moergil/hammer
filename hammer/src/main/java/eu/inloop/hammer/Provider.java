package eu.inloop.hammer;

public interface Provider {
    <T> T provide(Class<T> component);
}
