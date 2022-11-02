package peaksoft.service;

import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {

    void saveTask(Long id,Task task);

    void updateTask(Long id, Task task);

    List<Task> getAllTaskByLessonId(Long id);

    void deleteTaskById(Long id);


}
