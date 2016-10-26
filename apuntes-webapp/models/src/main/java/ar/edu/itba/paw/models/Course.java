package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courses_course_id_seq")
    @SequenceGenerator(sequenceName = "courses_course_id_seq", name = "courses_course_id_seq", allocationSize = 1)
    @Column(name = "course_id")
    private Integer courseid;

    @Column(length = 5, nullable = false, unique = true)
    private String code;

    @Column(length = 200, nullable = false)
    private String name;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseProgramRelation> programs;

    /* package */ Course() {
        // Just for Hibernate, we love you!
    }

    public Course(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    public int getCourseid() {
        return courseid;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseid != course.courseid) return false;
        return code != null ? code.equals(course.code) : course.code == null;

    }

    @Override
    public int hashCode() {
        int result = courseid;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", code, name);
    }

    public List<CourseProgramRelation> getRelatedPrograms() {
        return programs;
    }
}
