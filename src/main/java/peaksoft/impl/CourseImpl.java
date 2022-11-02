package peaksoft.impl;

import peaksoft.dao.CourseDao;
import peaksoft.entity.Course;
import peaksoft.service.CourseService;

import java.util.List;

public class CourseImpl implements CourseService {

    private final CourseDao courseDao = new CourseDao();
    private Course course = new Course();

    @Override
    public void saveCourse(Course course) {
        try {
            courseDao.saveCourse(new Course(course.getCourseName(), course.getDuration(),
                    course.getCreateAt(), course.getImageLink(), course.getDescription()));
            System.out.println("Курс по имени " + course.getCourseName()+" успешно добавлен!");
        } catch (Exception e) {
            System.out.println("КУРС НЕ СОХРАНЕН!");
        }
    }

    @Override
    public Course getCourseById(Long id) {
        try {
            if (courseDao.getCourseById(id) != null) {
                return courseDao.getCourseById(id);
            } else System.out.println("КУРС НЕ НАЙДЕН!");
        } catch (Exception e) {
            System.out.println("КУРС НЕ НАЙДЕН!");
        }
        return null;
    }

    @Override
    public List<Course> getAllCourse() {
        try {
            if (courseDao.getAllCourse().size() != 0) {
                return courseDao.getAllCourse();
            } else System.out.println("КУРСОВ НЕТУ!");
        } catch (Exception e) {
            System.out.println("КУРСОВ НЕТУ!");
        }
        return null;
    }

    @Override
    public void updateCourse(Long id, Course course) {
        try {
            courseDao.updateCourse(id, course);
        } catch (Exception e) {
            System.out.println("КУРС НЕ ОБНАВЛЕН!");
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        try {
            courseDao.deleteCourseById(id);
            System.out.println("Курс по имени "+course.getCourseName()+" успешно удален!");
        } catch (Exception e) {
            System.out.println("КУРС НЕ УДАЛЕН!");
        }
    }

    @Override
    public Course getCourseByName(String name) {
        try {
            if (courseDao.getCourseByName(name) != null) {
                return courseDao.getCourseByName(name);
            } else System.out.println("Курс по имени " + name + "не найден!");
        } catch (Exception e) {
            System.out.println("Курс по имени " + name + " не найден!");
        }
        return null;
    }
}
