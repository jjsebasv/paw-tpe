package ar.edu.itba.paw.forms.admin.validators;

import ar.edu.itba.paw.forms.admin.CourseProgramRelationCreateAdminForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseProgramRelationAdminFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CourseProgramRelationCreateAdminForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CourseProgramRelationCreateAdminForm relationAdminForm = (CourseProgramRelationCreateAdminForm) target;


    }
}
