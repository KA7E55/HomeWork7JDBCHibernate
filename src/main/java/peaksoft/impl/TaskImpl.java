package peaksoft.impl;

import peaksoft.dao.TaskDao;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;

import java.util.List;

public class TaskImpl implements TaskService {

    private final TaskDao taskDao = new TaskDao();

    @Override
    public void saveTask(Long id, Task task) {
        try {
            taskDao.saveTask(id, task);
            System.out.println("Задача по названию " + task.getName() + " успешно сохранена!");
        } catch (Exception e) {
            System.out.println("ЗАДАЧА НЕ СОХРАНЕНА!");
        }
    }

    @Override
    public void updateTask(Long id, Task task) {
        try {
            taskDao.updateTask(id, task);
            System.out.println("Задача по названию " + task.getName() + " успешно обновлена!");
        } catch (Exception e) {
            System.out.println("ЗАДАЧА НЕ СОХРАНЕНА!");
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long id) {
        try {
            if (taskDao.getAllTaskByLessonId(id).size() != 0) {
                return taskDao.getAllTaskByLessonId(id);
            } else System.out.println("ЗАДАЧИ НЕТУ!");
        } catch (Exception e) {
            System.out.println("ЗАДАЧИ НЕТУ!");
        }
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {
        try {
            taskDao.deleteTaskById(id);
            System.out.println("ЗАДАЧА УСПЕШНО УДАЛЕНА!");
        } catch (Exception e) {
            System.out.println("ЗАДАЧА НЕ УДАЛЕНА!");
        }
    }
}
