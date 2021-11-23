package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void testGetTasks() throws Exception {
        //Given
        List<Task> taskList =
                List.of(new Task(1L, "Test-TaskList", "Test"));
        List<TaskDto> taskDtoList =
                List.of(new TaskDto( 1L,"Test-TaskDtoList", "taskDtoList has taskList"));

        //When
        when(dbService.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                // getTasks fields
                .andExpect(MockMvcResultMatchers.jsonPath
                        ("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath
                        ("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath
                        ("$[0].title", Matchers.is("Test-TaskDtoList")))
                .andExpect(MockMvcResultMatchers.jsonPath
                        ("$[0].content", Matchers.is("taskDtoList has taskList")));
    }

    @Test
    void testGetTask() throws Exception {
        //Given
        Task task = new Task( 1L,"Test-Task", "Test");
        Long getId = task.getId();
        TaskDto taskDto = new TaskDto( 1L,"Test-TaskDto", "taskDto has task");

        //When
        when(dbService.getTask(getId)).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getTask")
                        .contentType(MediaType.APPLICATION_JSON))
                // getTask fields
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test-TaskDto")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("taskDto has task")));
    }


    @Test
    void testGetFindByIdTask() throws Exception {
        //Given
        Task task = new Task( 1L,"Test-Task", "Test");
        Long getId = task.getId();
        TaskDto taskDto = new TaskDto( 1L,"Test-TaskDto", "taskDto has task");

        //When
        when(dbService.getFindByIdTask(getId)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/task/getByIdTask")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                // getFindByIdTask fields
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test-TaskDto")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("taskDto has task")));
    }

    @Test
    void testCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "Test-Task", "Test");
        TaskDto taskDto = new TaskDto(1L, "Test-TaskDto", "Test");

        //When
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent)
                )
                //createTask
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));

    }

    @Test
    void testUpdateTask(){
        //Given
        Task task = new Task(1L, "Test-Task", "Test");
        TaskDto taskDto = new TaskDto(1L, "Test-TaskDto", "Test");

        //When
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //Then
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .put("/v1/task/updateTask")
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding("UTF-8")
                            .content(jsonContent)
                    )
                    //updateTask
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            System.out.println("Method has been finished");
        }
    }

    @Test
    void testDeleteTask(){
        //Given
        Task task = new Task(1L, "Test-Task", "Test");
        Long taskId = task.getId();
        TaskDto taskDto = new TaskDto(1L, "Test-TaskDto", "Test");

        //When
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(taskId)).thenReturn(Optional.ofNullable(task));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //Then
        try {
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .delete("/v1/task/deleteTask")
                            .contentType(MediaType.APPLICATION_JSON)
                            .characterEncoding("UTF-8")
                            .content(jsonContent)
                    )
                    //deleteTask
                    .andExpect(MockMvcResultMatchers.status().is(200));
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            System.out.println("Method has been finished");
        }
    }
}
