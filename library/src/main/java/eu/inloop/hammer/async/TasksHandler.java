package eu.inloop.hammer.async;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TasksHandler {
    private Map<Object, Set<TaskHandle>> tagHandles = new HashMap<>();
    private Map<TaskHandle, Set<Object>> handleTags = new HashMap<>();

    public void addHandle(TaskHandle handle) {
        addHandle(this, handle);
    }

    public void addHandle(Object tag, TaskHandle handle) {
        Set<TaskHandle> handles = tagHandles.get(tag);
        if (handles == null) {
            handles = tagHandles.put(tag, new HashSet<TaskHandle>());
        }
        handles.add(handle);

        Set<Object> tags = handleTags.get(handle);
        if (tags == null) {
            tags = handleTags.put(handle, new HashSet<>());
        }
        tags.add(tag);
    }

    public void removeHandle(TaskHandle handle) {
        Set<Object> tags = handleTags.get(handle);

        if (tags != null) {
            for (Object tag : tags) {
                Set<TaskHandle> handles = tagHandles.get(tag);
                if (handles != null) {
                    handles.remove(handle);
                    if (handles.isEmpty()) {
                        tagHandles.remove(tag);
                    }
                }
            }
        }

        handleTags.remove(handle);
    }

    public void cancel(TaskHandle handle) {
        handle.cancel();

        removeHandle(handle);
    }

    public void cancel(Object tag) {
        Set<TaskHandle> handles = tagHandles.get(tag);

        if (handles == null) {
            return;
        }

        for (TaskHandle handle : handles) {
            handle.cancel();

            Set<Object> tags = handleTags.get(handle);
            if (tags != null) {
                tags.remove(tag);
                if (tags.isEmpty()) {
                    handleTags.remove(handle);
                }
            }
        }
    }

    public void cancelAll() {
        for (Object tag : tagHandles.keySet()) {
            cancel(tag);
        }

        tagHandles.clear();
        handleTags.clear();
    }
}
