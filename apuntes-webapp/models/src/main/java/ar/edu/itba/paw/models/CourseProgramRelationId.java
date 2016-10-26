package ar.edu.itba.paw.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CourseProgramRelationId implements Serializable {

    @Column
    private int course_id;

    @Column
    private int program_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseProgramRelationId that = (CourseProgramRelationId) o;

        if (course_id != that.course_id) return false;
        return program_id == that.program_id;

    }

    @Override
    public int hashCode() {
        int result = course_id;
        result = 31 * result + program_id;
        return result;
    }

    public int getCourse_id() {

        return course_id;
    }

    public int getProgram_id() {
        return program_id;
    }
}
