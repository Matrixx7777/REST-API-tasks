package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L,"Test", "Test");

        //When
        Task mapToTask = taskMapper.mapToTask(taskDto);

        //Then
        assertThat(mapToTask).isNotNull();
        assertEquals(mapToTask.getId(),1L);
    }

    @Test
    public void testMapToTaskDto(){
        //Given
        Task task = new Task(1L,"Test", "Test");

        //When
        TaskDto mapToTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertThat(mapToTaskDto).isNotNull();
        assertEquals(mapToTaskDto.getId(),1L);
    }

    @Test
    public void testMapToTaskDtoList(){
        //Given
        Task task1 = new Task(1L,"Test-1", "Test-1");
        Task task2 = new Task(2L,"Test-2", "Test-2");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> mapToTaskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertThat(mapToTaskDtoList).isNotNull();
        assertEquals(mapToTaskDtoList.size(),2);
    }
}