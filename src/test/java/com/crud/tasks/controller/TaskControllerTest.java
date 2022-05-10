package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @InjectMocks
    private TaskController controller;

    @Mock
    private DbService service;

    @Mock
    private TaskMapper mapper;

    @Test
    void shouldFetchEmptyList() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "Test_Task", "Task_content"));
        when(service.getAllTasks()).thenReturn(tasks);
        when(mapper.mapToTaskDtoList(anyList())).thenReturn(List.of());

        //When
        List<TaskDto> tasksDto = controller.getTasks();

        //Then
        assertThat(tasksDto).isNotNull();
        assertThat(tasksDto.size()).isEqualTo(0);
    }

    @Test
    void testGetTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Test_Task", "Task_content");
        TaskDto taskDto = new TaskDto(1L, "Test_Task", "Task_content");
        when(service.getTask(1L)).thenReturn(task);
        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        ResponseEntity<TaskDto> tasksDto = controller.getTask(1L);

        //Then
        assertThat(tasksDto).isNotNull();
        assertThat(tasksDto.getStatusCode().is2xxSuccessful()).isTrue();
        assertEquals("Test_Task", tasksDto.getBody().getTitle());
        assertEquals("Task_content", tasksDto.getBody().getContent());
    }

    @Test
    void testDeleteTask() throws TaskNotFoundException {
        //Given
        //When
        ResponseEntity<Void> result = controller.deleteTask(1L);
        //Then
        assertThat(result.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    void testUpdateTask() {
        //Given
        Task task = new Task(1L, "Task", "content");
        Task savedTask = new Task(1L, "Task", "content");
        TaskDto taskDto = new TaskDto(1L, "Task", "content");
        TaskDto savedTaskDto = new TaskDto(1L, "Task", "content");

        when(service.saveTask(task)).thenReturn(savedTask);
        when(mapper.mapToTask(taskDto)).thenReturn(task);
        when(mapper.mapToTaskDto(savedTask)).thenReturn(savedTaskDto);
        //When
        ResponseEntity<TaskDto> updatedTask = controller.updateTask(taskDto);
        //Then
        assertThat(updatedTask.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(updatedTask.getBody().getId()).isEqualTo(1L);
    }
}
