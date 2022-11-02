package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.util.Util;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "task_name")
    private String name;
    @Column(name = "dead_line")
    private LocalDate deadLine;
    private String task;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;


    public Task(String name, LocalDate deadLine, String task) {
        this.name = name;
        this.deadLine = deadLine;
        this.task = task;

    }

    private Lesson lessonId(Long id) {
        SessionFactory sessionFactory = Util.getSessionFactory();
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
    public String toString() {
        return "- - - Task - - -" +
                "\nID: " + id +
                "\nName: " + name +
                "\nDeadLine: " + deadLine +
                "\nTask: " + task;
    }

}
