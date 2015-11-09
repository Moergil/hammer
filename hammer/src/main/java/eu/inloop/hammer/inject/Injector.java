package eu.inloop.hammer.inject;

import android.content.Context;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Injector {

    private static Map<String, Object> components;

    public static void init(Class<? extends ObjectGraph> objectGraphType, Context context) {
        components = new HashMap<>();

        try {
            objectGraphType.newInstance().build(context);

            injectDependenciesToComponents();
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Can not create object graph for type: " + objectGraphType.getName(), e);
        }
    }

    private static void injectDependenciesToComponents()
    {
        for (Object component : components.values())
        {
            Class componentType = component.getClass();
            while (true) {
                if (componentType.getAnnotation(InjectDependencies.class) == null) {
                    break;
                }

                inject(component, componentType);

                componentType = componentType.getSuperclass();
            }

            if (component instanceof Injectable) {
                Injectable injectable = (Injectable) component;
                injectable.onAfterInject();
            }
        }
    }

    public static void registerComponent(Object object)
    {
        registerComponent(object.getClass(), object);
    }

    public static void registerComponent(Class type, Object object) {
        components.put(type.getName(), object);
    }

    public static <T> T get(Class<T> type) {
        if (components == null) {
            throw new IllegalStateException("Injector mechanism not initialized.");
        }

        T object = (T) components.get(type.getName());

        if (object == null) {
            throw new IllegalStateException("No component found for type " + type.getName());
        }

        return object;
    }

    public static void inject(Object target)
    {
        inject(target, target.getClass());
    }

    private static void inject(Object target, Class componentType) {
        Field[] fields = componentType.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getAnnotation(Inject.class) != null) {
                    injectDirect(target, field);
                }
            } catch (Exception e) {
                String message = String.format(
                        "Injection failed for target %s, field %s (type %s)",
                        target.toString(),
                        field.getName(),
                        field.getType().getName());
                throw new IllegalStateException(message, e);
            }
        }
    }

    private static void injectDirect(Object target, Field field) throws Exception {
        Class fieldClass = field.getType();

        Object component = get(fieldClass);

        if (component == null) {
            throw new NullPointerException();
        }

        field.setAccessible(true);
        field.set(target, component);
    }
}
