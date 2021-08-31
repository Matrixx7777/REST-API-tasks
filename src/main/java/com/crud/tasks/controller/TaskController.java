package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {


    private final DbService service;
    private final TaskMapper taskMapper;

//    @RequestMapping(method = RequestMethod.GET, value = "getTask")
//    public TaskDto getTask(Long taskId) {
//        return new TaskDto(1L, "test title", "test_content");
//    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long id) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(
                service.getTask(id).orElseThrow(TaskNotFoundException::new)
        );
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public TaskDto deleteTask(@RequestParam Long id) {
        Task deleteTask = service.deleteTask(id);
        return taskMapper.mapToTaskDto(deleteTask);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return taskMapper.mapToTaskDto(savedTask);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }


//    @Autowired
//    public TaskController(DbService service, TaskMapper taskMapper) {
//        this.service = service;
//        this.taskMapper = taskMapper;
//    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getByIdTask")
    public TaskDto getByIdTask(@RequestParam Long id) {
        Task task = service.getFindByIdTask(id);
        return taskMapper.mapToTaskDto(task);
    }
}