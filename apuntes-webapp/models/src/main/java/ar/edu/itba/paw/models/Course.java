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
    private Long courseid;

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

    public long getCourseid() {
        return courseid;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
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
        return courseid.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s - %s", code, name);
    }

    public List<CourseProgramRelation> getRelatedPrograms() {
        return programs;
    }
}
