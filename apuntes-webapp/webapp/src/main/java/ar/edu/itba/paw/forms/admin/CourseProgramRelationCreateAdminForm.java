package ar.edu.itba.paw.forms.admin;

import ar.edu.itba.paw.interfaces.admin.IAdminForm;
import ar.edu.itba.paw.models.CourseProgramRelation;

import javax.validation.constraints.NotNull;


public class CourseProgramRelationCreateAdminForm implements IAdminForm<CourseProgramRelation> {

    @NotNull
    private Integer courseid;

    @NotNull
    private Integer programid;

    @NotNull
    private Integer semester;

    @Override
    public CourseProgramRelation buildObjectFromForm() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadValuesFromInstance(CourseProgramRelation instance) {
        setCourseid((int) instance.getCourse().getCourseid());
        setProgramid((int) instance.getProgram().getProgramid());
        setSemester(instance.getSemester());
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getProgramid() {
        return programid;
    }

    public void setProgramid(Integer programid) {
        this.programid = programid;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
