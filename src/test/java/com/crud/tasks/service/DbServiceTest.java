package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService service;

    @Mock
    private TaskRepository repository;

    @Test
    void testGetAllTasks() {
        //Given
        when(repository.findAll()).thenReturn(List.of());
        //When
        List<Task> tasks = service.getAllTasks();
        //Then
        assertThat(tasks).isNotNull();
        assertThat(tasks.size()).isEqualTo(0);
    }

    @Test
    void testSaveTask() {
        //Given
        Task task = new Task(1L, "Test", "Test_content");
        Task savedTask = new Task(1L, "Test", "Test_content");
        when(repository.save(task)).thenReturn(savedTask);
        //When
        Task resultTask = service.saveTask(task);
        //Then
        assertThat(resultTask).isNotNull();
        assertEquals(1L, resultTask.getId());
        assertEquals("Test", resultTask.getTitle());
        assertEquals("Test_content", resultTask.getContent());
    }

    @Test
    void testGetTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "Test", "Test_content");
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        //When
        Task result = service.getTask(1L);
        //Then
        assertThat(result).isNotNull();
        assertEquals(1L, result.getId());
    }
}
