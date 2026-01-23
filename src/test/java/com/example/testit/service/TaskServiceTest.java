package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Status;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.TaskRepository;
import com.example.testit.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Service
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MailService mailService;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private User user(Long id, String name) {
        User u =new User(name);
        u.setId(id);
        return u;
    }
    @Test
    void createTask_ok() {
        User requested= user(10L, "req");
        User assigned= user(10L, "ass");

        when(userRepository.findById(1L)).thenReturn(Optional.of(requested));
        when(userRepository.findById(2L)).thenReturn(Optional.of(assigned));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task task= taskService.createTask("my task", "Description", 1L, 2L);

        assertNotNull(task);
        assertEquals("my task", task.getTitle());
        assertEquals(Status.OUVERT, task.getStatus());
    }

}

