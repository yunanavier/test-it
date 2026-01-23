package com.example.testit.controller;

import com.example.testit.adapter.user.CurrentUserService;
import com.example.testit.model.Task;
import com.example.testit.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)

class TaskControllerTest {
    @MockBean
    private CurrentUserService currentUserService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void getAllTasks_shouldReturn200() throws Exception {

        when(taskService.findAll())
                .thenReturn(List.of(new Task(), new Task()));
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    void getTaskById_shouldReturn200() throws Exception {

        Task task = new Task();
        task.setId(1L);

        when(taskService.findById(1L))
                .thenReturn(Optional.of(task));
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk());
    }

}


