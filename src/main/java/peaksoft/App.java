package peaksoft;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.impl.CourseImpl;
import peaksoft.impl.InstructorImpl;
import peaksoft.impl.LessonImpl;
import peaksoft.impl.TaskImpl;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * @KA7E55
 */


public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {

        app();

    }
    static void buttons() {
        System.out.println(" - - - - - Кнопки - - - - - ");
        System.out.println("Нажмите -> 1 saveCourse()");
        System.out.println("Нажмите -> 2 getCourseById()");
        System.out.println("Нажмите -> 3 getAllCourse()");
        System.out.println("Нажмите -> 4 updateCourse()");
        System.out.println("Нажмите -> 5 deleteCourseById()");
        System.out.println("Нажмите -> 6 getCourseByName()");
        System.out.println();
        System.out.println("Нажмите -> 7 saveInstructor()");
        System.out.println("Нажмите -> 8 updateInstructor()");
        System.out.println("Нажмите -> 9 getInstructorById()");
        System.out.println("Нажмите -> 10 getInstructorsByCourseId()");
        System.out.println("Нажмите -> 11 deleteInstructorById()");
        System.out.println("Нажмите -> 12 assignInstructorToCourse()");
        System.out.println();
        System.out.println("Нажмите -> 13 saveLesson()");
        System.out.println("Нажмите -> 14 updateLesson()");
        System.out.println("Нажмите -> 15 getLessonById()");
        System.out.println("Нажмите -> 16 getLessonsByCourseId()");
        System.out.println();
        System.out.println("Нажмите -> 17 saveTask()");
        System.out.println("Нажмите -> 18 updateTask()");
        System.out.println("Нажмите -> 19 getAllTaskByLessonId()");
        System.out.println("Нажмите -> 20 deleteTaskById()");
    }

    static void app() {

        CourseImpl courseImpl = new CourseImpl();
        InstructorImpl instructorImpl = new InstructorImpl();
        LessonImpl lessonImpl = new LessonImpl();
        TaskImpl taskImpl = new TaskImpl();

        String number = "null";
        while (!number.equals("!")) {
            buttons();
            System.out.println();
            System.out.println("Выберите кнопку:");
            number = scanner.nextLine();
            try {
                if (Character.isDigit(number.charAt(0))) {
                    switch (number) {
                        case "1": {
                            Course course = new Course();
                            System.out.println("Напишите название курса: ");
                            course.setCourseName(scanner.nextLine());
                            System.out.println("Введите продолжительность: число ");
                            course.setDuration(scanner.nextInt());
                            System.out.println("Напишите ссылку на картинку: ");
                            course.setImageLink(scanner.nextLine());
                            course.setImageLink(scanner.nextLine());
                            System.out.println("Напишите описание: ");
                            course.setDescription(scanner.nextLine());


                            course.setCreateAt(LocalDate.of(2020, 12, 12));
                            courseImpl.saveCourse(course);
                        }
                        break;
                        case "2": {
                            System.out.println("Напишите ID курса: ");
                            long id = scanner.nextLong();
                            System.out.println(courseImpl.getCourseById(id));
                        }
                        case "3": {
                            for (Course course : courseImpl.getAllCourse()) {
                                System.out.println(course);
                            }
                        }
                        break;
                        case "4": {
                            System.out.println("Напишите ID курса, который вы хотите обновить: ");
                            long id = scanner.nextLong();
                            Course course = new Course();
                            System.out.println("Напишите название курса: ");
                            String courseName = scanner.nextLine();
                            course.setCourseName(courseName);
                            System.out.println("Введите продолжительность: ");
                            int duration = scanner.nextInt();
                            course.setDuration(duration);
                            System.out.println("Напишите ссылку на картинку: ");
                            String imageLink = scanner.nextLine();
                            course.setImageLink(imageLink);
                            System.out.println("Напишите описание: ");
                            String description = scanner.nextLine();
                            course.setDescription(description);
                            System.out.println("Напишите год создания курса по адресу: ");
                            int year = scanner.nextInt();
                            while (year < 2000) {
                                year = scanner.nextInt();
                            }
                            int month = random.nextInt();
                            int day = random.nextInt();
                            course.setCreateAt(LocalDate.of(year, month, day));
                            courseImpl.updateCourse(id, course);
                        }
                        break;
                        case "5": {
                            System.out.println("Напишите ID курса: ");
                            long id = scanner.nextLong();
                            courseImpl.deleteCourseById(id);
                        }
                        break;
                        case "6": {
                            System.out.println("Напишите название курса: ");
                            String name = scanner.nextLine();
                            System.out.println(courseImpl.getCourseByName(name));
                        }
                        break;
                        case "7": {
                            Instructor instructor = new Instructor();
                            System.out.println("Напишите имя инструктора: ");
                            String firstName = scanner.nextLine();
                            instructor.setFirstName(firstName);
                            System.out.println("Напишите фамилия инструктора: ");
                            String lastName = scanner.nextLine();
                            instructor.setLastName(lastName);
                            System.out.println("Напишите email инструктора: ");
                            String email = scanner.nextLine();
                            instructor.setEmail(email);
                            System.out.println("Напишите номер телефона инструктора: ");
                            String phoneNumber = scanner.nextLine();
                            instructor.setPhoneNumber(phoneNumber);
                            instructorImpl.saveInstructor(instructor);
                        }
                        break;
                        case "8": {
                            System.out.println("Напишите ID инструктора, которого вы хотите обновить: ");
                            long id = scanner.nextLong();

                            Instructor instructor = new Instructor();
                            System.out.println("Напишите имя инструктора: ");
                            String firstName = scanner.nextLine();
                            String firstName1 = scanner.nextLine();
                            instructor.setFirstName(firstName1);
                            System.out.println("Напишите фамилия инструктора: ");
                            String lastName = scanner.nextLine();
                            instructor.setLastName(lastName);
                            System.out.println("Напишите email инструктора: ");
                            String email = scanner.nextLine();
                            instructor.setEmail(email);
                            System.out.println("Напишите номер телефона инструктора: ");
                            String phoneNumber = scanner.nextLine();
                            instructor.setPhoneNumber(phoneNumber);
                            instructorImpl.updateInstructor(id, instructor);
                        }
                        break;
                        case "9": {
                            System.out.println("Напишите ID инструктора: ");
                            long id = scanner.nextLong();
                            System.out.println(instructorImpl.getInstructorById(id));
                        }
                        break;
                        case "10": {
                            System.out.println("Напишите ID курса, чтобы получить всех инструкторов: ");
                            long id = scanner.nextLong();
                            for (Instructor instructor : instructorImpl.getInstructorsByCourseId(id)) {
                                System.out.println(instructor);
                            }
                        }
                        break;
                        case "11": {
                            System.out.println("Удалить инструктора по ID: ");
                            long id = scanner.nextLong();
                            instructorImpl.deleteInstructorById(id);
                        }
                        break;
                        case "12": {
                            System.out.println("Напишите ID ипструктора: ");
                            long instructorId = scanner.nextLong();
                            System.out.println("Напишите ID курса: ");
                            long courseId = scanner.nextLong();
                            instructorImpl.assignInstructorToCourse(instructorId, courseId);
                        }
                        break;
                        case "13": {
                            System.out.println("Напишите название урока: ");
                            String nameLesson = scanner.nextLine();
                            String nameLesson1 = scanner.nextLine();
                            System.out.println("Напишите ссылку на видео: ");
                            String videoLink = scanner.nextLine();
                            System.out.println("Напишите ID курса: ");
                            long id = scanner.nextLong();
                            lessonImpl.saveLesson(new Lesson(nameLesson, videoLink, id));
                            lessonImpl.saveLesson(new Lesson(nameLesson1, videoLink, id));
                        }
                        break;
                        case "14": {
                            System.out.println("Напишите ID урока для обновления: ");
                            long id = scanner.nextLong();
                            Lesson lesson = new Lesson();
                            System.out.println("Напишите название урока: ");
                            String lessonName = scanner.nextLine();
                            lesson.setName(lessonName);
                            System.out.println("Напишите ссылку на видео: ");
                            lesson.setVideoLink(scanner.nextLine());
                            lessonImpl.updateLesson(id, lesson);
                        }
                        break;
                        case "15": {
                            System.out.println("Напишите ID урока ");
                            long id = scanner.nextLong();
                            System.out.println(lessonImpl.getLessonById(id));
                        }
                        break;
                        case "16": {
                            System.out.println("Напишите ID курса, чтобы получить все уроки:");
                            long id = scanner.nextLong();
                            for (Lesson lessons : lessonImpl.getLessonsByCourseId(id)) {
                                System.out.println(lessons);
                            }
                        }
                        break;
                        case "17": {
                            System.out.println("Напишите название задачи: ");
                            String taskName = scanner.nextLine();
                            System.out.println("Напишите срок задачи: год");
                            int year = scanner.nextInt();
                            while (year < 2000) {
                                year = scanner.nextInt();
                            }
                            int month = random.nextInt(12);
                            int day = random.nextInt(31);
                            LocalDate localDate = LocalDate.of(year, month, day);
                            System.out.println("Напишите задание: ");
                            String name = scanner.nextLine();
                            String name1 = scanner.nextLine();
                            System.out.println("Напишите ID урока: ");
                            long id = scanner.nextLong();
                            taskImpl.saveTask(id, new Task(taskName, localDate, name1));
                        }
                        break;
                        case "18": {
                            Task task = new Task();
                            System.out.println("Напишите ID урока: ");
                            long id = scanner.nextLong();
                            System.out.println("Напишите название урока: ");
                            task.setName(scanner.nextLine());
                            int year = random.nextInt(2022);
                            int month = random.nextInt(12);
                            int day = random.nextInt(31);
                            LocalDate localDate = LocalDate.of(year, month, day);
                            task.setDeadLine(localDate);
                            System.out.println("Напишите задание: ");
                            task.setTask(scanner.nextLine());
                            taskImpl.updateTask(id, task);
                        }
                        break;
                        case "19": {
                            System.out.println("Напишите ID урока, чтобы получить все задания: ");
                            long id = scanner.nextLong();
                            for (Task tasks : taskImpl.getAllTaskByLessonId(id)) {
                                System.out.println(tasks);
                            }
                        }
                        break;
                        case "20": {
                            System.out.println("Удалить задачу по ID: ");
                            long id = scanner.nextLong();
                            taskImpl.deleteTaskById(id);
                        }
                    }
                } else {
                    throw new RuntimeException();
                }
            } catch (RuntimeException re) {
                System.out.println("Нету такой кнопки!");
            }
        }
    }
}
