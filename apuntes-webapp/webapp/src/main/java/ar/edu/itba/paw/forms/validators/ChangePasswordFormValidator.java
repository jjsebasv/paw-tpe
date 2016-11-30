package ar.edu.itba.paw.forms.validators;

import ar.edu.itba.paw.forms.ChangePasswordForm;
import ar.edu.itba.paw.forms.ClientForm;
import ar.edu.itba.paw.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChangePasswordFormValidator implements Validator {

    private final ClientService clientService;

    @Autowired
    public ChangePasswordFormValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    public boolean supports(Class clazz) {
        return ChangePasswordForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {

        ChangePasswordForm changePasswordForm = (ChangePasswordForm) obj;

        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "models.client.password.notmatch");
        }

        if (!changePasswordForm.getClient().getPassword().equals(changePasswordForm.getOldPassword())) {
            errors.rejectValue("oldPassword", "models.client.password.notmatch.old");
        }

        if (changePasswordForm.getClient().getPassword().equals(changePasswordForm.getNewPassword())) {
            errors.rejectValue("oldPassword", "models.client.password.match.old");
        }

    }
}