package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "coursesToPrograms")
public class CourseProgramRelation {

    @EmbeddedId
    private CourseProgramRelationId id;

    @ManyToOne
    @MapsId("program_id")
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "semester")
    private int semester;

    public CourseProgramRelation() {
    }

    public Program getProgram() {
        return program;
    }

    public Course getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public CourseProgramRelation(Program program, Course course, int semester) {
        this.program = program;
        this.course = course;
        this.semester = semester;

        this.id = new CourseProgramRelationId(course.getCourseid(), program.getProgramid());
    }
}