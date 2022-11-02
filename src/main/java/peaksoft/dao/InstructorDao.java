package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.InstructorService;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class InstructorDao implements InstructorService {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void saveInstructor(Instructor instructor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new Instructor(instructor.getFirstName(), instructor.getLastName(), instructor.getEmail(), instructor.getPhoneNumber()));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Instructor newInstructor = session.get(Instructor.class, id);
            newInstructor.setFirstName(instructor.getFirstName());
            newInstructor.setLastName(instructor.getLastName());
            newInstructor.setEmail(instructor.getEmail());
            newInstructor.setPhoneNumber(instructor.getPhoneNumber());
            session.saveOrUpdate(newInstructor);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
            return instructor;
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course c where c.id = :id", Course.class).setParameter("id", id).list();
            Course course = courses.get(0);
            List<Instructor> instructors = new ArrayList<>(course.getInstructors());
            session.getTransaction().commit();
            return instructors;
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteInstructorById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Instructor instructor = session.find(Instructor.class, id);
            session.remove(instructor);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new RuntimeException();
        }

    }

    @Override
    public void assignInstructorToCourse(Long id, Long courseId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            Course course = session.get(Course.class, courseId);
            instructor.getCourses().add(course);
            course.getInstructors().add(instructor);
            session.saveOrUpdate(instructor);
            session.saveOrUpdate(course);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            throw new RuntimeException();
        }
    }
}
