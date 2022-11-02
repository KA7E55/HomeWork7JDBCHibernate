package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "lesson_name")
    private String name;
    @Column(name = "video_link")
    private String videoLink;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, mappedBy = "lesson")
    private List<Task> task;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH, PERSIST}, fetch = LAZY)
    private Course course;

    public Lesson(String name, String videoLink, Long courseId) {
        this.name = name;
        this.videoLink = videoLink;
        this.course = courseId(courseId);

    }

    public void addTask(Task newTask) {
        this.task.add(newTask);
    }

    private Course courseId(Long id) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.getTransaction().commit();
            return course;
        } catch (HibernateException he) {
            throw new RuntimeException(he.getMessage());
        }
    }

    @Override
    public String toString() {
        return "- - - Lesson - - -" +
                "\nID: " + id +
                "\nName: " + name +
                "\nVideoLink: " + videoLink;
    }
}
