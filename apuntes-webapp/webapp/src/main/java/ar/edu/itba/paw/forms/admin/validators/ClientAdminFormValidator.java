package ar.edu.itba.paw.forms.admin.validators;

import ar.edu.itba.paw.forms.admin.ClientAdminForm;
import ar.edu.itba.paw.models.ClientRole;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

@Component
public class ClientAdminFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ClientAdminForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ClientAdminForm clientAdminForm = (ClientAdminForm) target;

        if (!Arrays.stream(ClientRole.values()).anyMatch(clientRole -> clientRole.name().equals(clientAdminForm.getRole()))) {
            errors.rejectValue("role", "models.clients.role.invalid");
        }

    }
}
