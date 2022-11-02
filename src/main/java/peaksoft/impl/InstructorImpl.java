package peaksoft.impl;

import peaksoft.dao.InstructorDao;
import peaksoft.entity.Instructor;
import peaksoft.service.InstructorService;

import java.util.List;

public class InstructorImpl implements InstructorService {
    private final InstructorDao instructorDao = new InstructorDao();
    private final Instructor instructor = new Instructor();

    @Override
    public void saveInstructor(Instructor instructor) {
        try {
            instructorDao.saveInstructor(instructor);
            System.out.println("Инструктор по имени " + instructor.getFirstName() + " успешно сохранен!");
        } catch (Exception e) {
            System.out.println("ИНСТРУКТОР НЕ СОХРАНЕН!");
        }
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        try {
            instructorDao.updateInstructor(id, instructor);
            System.out.println("Инструктор по имени " + instructor.getFirstName() + " успешно обнавлен!");
        } catch (Exception e) {
            System.out.println("ИНСТРУКТОР НЕ ОБНАВЛЕН!");
        }
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try {
            if (instructorDao.getInstructorById(id) != null) {
                return instructorDao.getInstructorById(id);
            } else System.out.println("ИНСТРУКТОР НЕ НАЙДЕН!");
        } catch (Exception e) {
            System.out.println("ИНСТРУКТОР НЕ НАЙДЕН!");
        }
        return null;
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long id) {
        try {
            if (instructorDao.getInstructorsByCourseId(id).size() != 0) {
                return instructorDao.getInstructorsByCourseId(id);
            } else System.out.println("НЕТУ ИНСТРУКТОРОВ!");
        } catch (Exception e) {
            System.out.println("НЕТУ ИНСТРУКТОРОВ!");
        }
        return null;
    }

    @Override
    public void deleteInstructorById(Long id) {
        try {
            instructorDao.deleteInstructorById(id);
            System.out.println("Инструктор по имени "+instructor.getFirstName()+" успешно удален!");
        } catch (Exception e) {
            System.out.println("ИНСТРУКТОР НЕ УДАЛЕН!");
        }
    }

    @Override
    public void assignInstructorToCourse(Long id, Long courseId) {
        try {
            instructorDao.assignInstructorToCourse(id, courseId);
            System.out.println("Инструктор c ID " + id + " успешно назначен!");
        } catch (Exception e) {
            System.out.println("ИНСТРУКТОР НЕ НАЙДЕН В БАЗЕ ДАННЫХ!");
        }
    }
}
