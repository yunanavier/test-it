package com.example.testit.service;

import com.example.testit.model.Status;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TaskServiceIT {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createTask_integration() {
        User requester = userRepository.save(new User("requester"));
        User assigned  = userRepository.save(new User("assigned"));

        Task task = taskService.createTask(
                "Tâche IT",
                "Description",
                requester.getId(),
                assigned.getId()
        );
        assertNotNull(task.getId());
        assertEquals("Tâche IT", task.getTitle());
        assertEquals(Status.OUVERT, task.getStatus());
    }
}
