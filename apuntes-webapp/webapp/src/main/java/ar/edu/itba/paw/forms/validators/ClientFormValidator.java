package ar.edu.itba.paw.forms.validators;

import ar.edu.itba.paw.forms.ClientForm;
import ar.edu.itba.paw.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientFormValidator implements Validator {

    private final ClientService clientService;

    @Autowired
    public ClientFormValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    public boolean supports(Class clazz) {
        return ClientForm.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {

        ClientForm clientForm = (ClientForm) obj;

        if (clientService.findByUsername(clientForm.getUsername()) != null) {
            errors.rejectValue("username", "already.exists");
        }

        if (!clientForm.getPassword().equals(clientForm.getRepeatPassword())) {
            errors.rejectValue("repeatPassword", "does.not.match");
        }

        if (clientService.findByEmail(clientForm.getEmail()) != null) {
            errors.rejectValue("email", "email.already.exists");
        }

    }
}