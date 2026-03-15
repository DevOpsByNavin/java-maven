package com.example.toytasks.service;

import com.example.toytasks.model.Task;
import com.example.toytasks.model.TaskRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Task> getAll() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> getById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Task create(TaskRequest request) {
        Long id = idCounter.getAndIncrement();
        Task task = new Task(id, request.getTitle(), request.isDone());
        tasks.put(id, task);
        return task;
    }

    public Optional<Task> update(Long id, TaskRequest request) {
        Task existing = tasks.get(id);
        if (existing == null) {
            return Optional.empty();
        }

        existing.setTitle(request.getTitle());
        existing.setDone(request.isDone());
        tasks.put(id, existing);
        return Optional.of(existing);
    }

    public boolean delete(Long id) {
        return tasks.remove(id) != null;
    }
}
