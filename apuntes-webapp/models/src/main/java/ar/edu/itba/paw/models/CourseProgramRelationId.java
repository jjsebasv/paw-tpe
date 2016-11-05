package ar.edu.itba.paw.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class CourseProgramRelationId implements Serializable {

    @Column
    private long course_id;

    @Column
    private long program_id;

    public CourseProgramRelationId(final long course_id, final long program_id) {
        this.course_id = course_id;
        this.program_id = program_id;
    }

    public CourseProgramRelationId() {
    }

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
        int result = (int) (course_id ^ (course_id >>> 32));
        result = 31 * result + (int) (program_id ^ (program_id >>> 32));
        return result;
    }

    public long getCourse_id() {

        return course_id;
    }

    public long getProgram_id() {
        return program_id;
    }
}
