package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.CourseService;
import peaksoft.util.Util;

import java.util.List;

public class CourseDao implements CourseService {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void saveCourse(Course course) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Course(course.getCourseName(), course.getDuration(),
                    course.getCreateAt(), course.getImageLink(), course.getDescription()));
            session.getTransaction().commit();
        }
    }

    @Override
    public Course getCourseById(Long id) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.getTransaction().commit();
            return course;
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Course> getAllCourse() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course c").getResultList();
            session.getTransaction().commit();
            return courses;
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateCourse(Long id, Course course) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Course newCourse = session.get(Course.class, id);
            newCourse.setCourseName(course.getCourseName());
            newCourse.setDuration(course.getDuration());
            newCourse.setCreateAt(course.getCreateAt());
            newCourse.setImageLink(course.getImageLink());
            newCourse.setDescription(course.getDescription());
            session.saveOrUpdate(newCourse);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            for (Instructor instructor : course.getInstructors()) {
                instructor.setCourses(null);
            }
            session.delete(course);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public Course getCourseByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course c").list();
            session.getTransaction().commit();
            for (Course course : courses) {
                if (course.getCourseName().equals(name)) {
                    return course;
                }
            }
            return null;
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }
}
