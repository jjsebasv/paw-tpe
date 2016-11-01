package ar.edu.itba.paw.forms.admin.validators;

import ar.edu.itba.paw.forms.admin.CourseAdminForm;
import ar.edu.itba.paw.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class CourseAdminFormValidator {

    private final CourseService courseService;

    @Autowired
    public CourseAdminFormValidator(CourseService courseService) {
        this.courseService = courseService;
    }

    public boolean supports(Class clazz) {
        return CourseAdminForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {

        CourseAdminForm courseAdminForm = (CourseAdminForm) obj;

        if (courseService.findByCode(courseAdminForm.getCode()) != null) {
            errors.rejectValue("code", "admin.models.courses.alreadyexist");
        }

    }
}

