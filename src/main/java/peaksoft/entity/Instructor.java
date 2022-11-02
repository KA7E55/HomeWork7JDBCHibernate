package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor

public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH, PERSIST}, fetch = LAZY)

    @JoinTable(name = "instructor_course", joinColumns = @JoinColumn(name =
            "instructor_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))

    private List<Course> courses = new ArrayList<>();

    public Instructor(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "- - - Instructor - - -" +
                "\nID: " + id +
                "\nFirstName: " + firstName +
                "\nLastName: " + lastName +
                "\nEmail: " + email +
                "\nPhoneNumber: " + phoneNumber;
    }
}
