package peaksoft.impl;

import peaksoft.dao.LessonDao;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;

import java.util.List;

public class LessonImpl implements LessonService {

    private final LessonDao lessonDao= new LessonDao() ;

    @Override
    public void saveLesson(Lesson lesson) {
        try {
            lessonDao.saveLesson(lesson);
            System.out.println("Урок по имени " + lesson.getName() + " успешно сохранен!");
        } catch (Exception e) {
            System.out.println("УРОК НЕ СОХРАНЕН!");
        }
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        try {
            lessonDao.updateLesson(id, lesson);
            System.out.println("Урок по имени " + lesson.getName() + " успешно обнавлен!");
        } catch (Exception e) {
            System.out.println("УРОК НЕ ОБНАВЛЕН!");
        }
    }

    @Override
    public Lesson getLessonById(Long id) {
        try {
            if (lessonDao.getLessonById(id) != null) {
                return lessonDao.getLessonById(id);
            } else System.out.println("УРОК НЕ НАЙДЕН!");
        } catch (Exception e) {
            System.out.println("УРОК НЕ НАЙДЕН!");
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long id) {
        try {
            if (lessonDao.getLessonsByCourseId(id).size() != 0) {
                return lessonDao.getLessonsByCourseId(id);
            } else System.out.println("КУРСОВ НЕТУ!");
        } catch (Exception e) {
            System.out.println("КУРСОВ НЕТУ!");
        }
        return null;
    }
}
