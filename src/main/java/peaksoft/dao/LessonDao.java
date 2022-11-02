package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class LessonDao implements LessonService {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void saveLesson(Lesson lesson) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(lesson);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Lesson newLesson = session.get(Lesson.class, id);
            newLesson.setName(lesson.getName());
            newLesson.setVideoLink(lesson.getVideoLink());
            session.saveOrUpdate(newLesson);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public Lesson getLessonById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Lesson lesson = session.get(Lesson.class, id);
            session.getTransaction().commit();
            return lesson;
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course c where c.id=:id", Course.class).setParameter("id", id).list();
            Course course = courses.get(0);
            List<Lesson> lessons = new ArrayList<>(course.getLessons());
            session.getTransaction().commit();
            return lessons;
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }
}
