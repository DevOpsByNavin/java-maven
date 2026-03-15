package com.example.toytasks.service;

import com.example.toytasks.model.Task;
import com.example.toytasks.model.TaskRequest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private final TaskService taskService = new TaskService();

    @Test
    void createAndGetTask() {
        TaskRequest request = new TaskRequest();
        request.setTitle("learn ci");
        request.setDone(false);

        Task created = taskService.create(request);

        Optional<Task> fromStore = taskService.getById(created.getId());
        assertTrue(fromStore.isPresent());
        assertEquals("learn ci", fromStore.get().getTitle());
        assertFalse(fromStore.get().isDone());
    }

    @Test
    void deleteTask() {
        TaskRequest request = new TaskRequest();
        request.setTitle("temporary");
        request.setDone(false);

        Task created = taskService.create(request);
        boolean deleted = taskService.delete(created.getId());

        assertTrue(deleted);
        assertTrue(taskService.getById(created.getId()).isEmpty());
    }
}
