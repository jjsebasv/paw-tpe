package ar.edu.itba.paw.forms.admin;

import ar.edu.itba.paw.interfaces.admin.IAdminForm;
import ar.edu.itba.paw.models.CourseProgramRelation;

import javax.validation.constraints.NotNull;


public class CourseProgramRelationUpdateAdminForm implements IAdminForm<CourseProgramRelation> {

    @NotNull
    private Integer semester;

    @Override
    public CourseProgramRelation buildObjectFromForm() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadValuesFromInstance(CourseProgramRelation instance) {
        setSemester(instance.getSemester());
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
