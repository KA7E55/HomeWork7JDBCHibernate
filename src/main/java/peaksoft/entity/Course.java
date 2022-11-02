package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private int duration;
    private LocalDate createAt;
    private String imageLink;
    private String description;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH, PERSIST}, fetch = LAZY, mappedBy = "courses")
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany(cascade = {DETACH, MERGE, REFRESH, PERSIST, REMOVE}, fetch = LAZY, mappedBy = "course")
    private List<Lesson> lessons = new ArrayList<>();

    public Course(String courseName, int duration, LocalDate createAt, String imageLink, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.createAt = createAt;
        this.imageLink = imageLink;
        this.description = description;

    }

    @Override
    public String toString() {
        return "- - - Course - - -" +
                "\nID: " + id +
                "\nCourseName: " + courseName +
                "\nDuration: " + duration +
                "\nCreateAt: " + createAt +
                "\nImageLink: " + imageLink +
                "\nDescription: " + description;
    }
}
