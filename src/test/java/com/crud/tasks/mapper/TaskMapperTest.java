package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {


    @Test
    void testMapToTask() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L, "test_task", "test_content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertThat(task).isNotNull();
        assertEquals(1L, task.getId());
        assertEquals("test_task", task.getTitle());
        assertEquals("test_content", task.getContent());
    }

    @Test
    void testMapToTaskDto() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L, "test_task", "test_content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertThat(taskDto).isNotNull();
        assertEquals(1L, taskDto.getId());
        assertEquals("test_task", taskDto.getTitle());
        assertEquals("test_content", taskDto.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        List<Task> taskList = List.of(new Task(1L, "test_task", "test_content"));
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertThat(taskDtos).isNotNull();
        assertThat(taskDtos.size()).isEqualTo(1);
    }
}
