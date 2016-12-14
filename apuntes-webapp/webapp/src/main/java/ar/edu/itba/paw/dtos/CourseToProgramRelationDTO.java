package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.CourseProgramRelation;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CourseToProgramRelationDTO {

    private long programId;

    private long courseId;

    private int semester;

    public CourseToProgramRelationDTO(long programId, long courseId, int semester) {
        this.programId = programId;
        this.courseId = courseId;
        this.semester = semester;
    }

    public CourseToProgramRelationDTO(final CourseProgramRelation relation) {
        this.programId = relation.getProgram().getProgramid();
        this.courseId = relation.getCourse().getCourseid();
        this.semester = relation.getSemester();
    }

    public CourseToProgramRelationDTO() {
    }


    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
