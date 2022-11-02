package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class TaskDao implements TaskService {
    private SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void saveTask(Long id, Task task) {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();

            Lesson lesson = session.find(Lesson.class, id);
            task.setLesson(lesson);
            lesson.addTask(task);
            session.merge(lesson);

            session.getTransaction().commit();
            session.close();
        } catch (Exception exception) {
            System.out.println("Exception (saveTask)");
        }
    }

    @Override
    public void updateTask(Long id, Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Task newTask = session.get(Task.class, id);
            newTask.setName(task.getName());
            newTask.setTask(task.getTask());
            newTask.setDeadLine(task.getDeadLine());
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Lesson lesson = session.find(Lesson.class, id);
            List<Task> tasks = new ArrayList<>(lesson.getTask());
            session.getTransaction().commit();
            return tasks;
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteTaskById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.delete(task);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            System.out.println(he.getMessage());
        }
    }
}
