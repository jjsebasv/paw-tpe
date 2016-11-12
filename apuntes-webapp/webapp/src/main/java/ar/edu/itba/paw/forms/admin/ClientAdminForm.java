package ar.edu.itba.paw.forms.admin;

import ar.edu.itba.paw.models.builders.ClientBuilder;
import ar.edu.itba.paw.interfaces.admin.IAdminForm;
import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class ClientAdminForm implements IAdminForm<Client> {

    @Size(min = 6, max = 100)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String username;

    @Size(min = 6, max = 100)
    private String password;

    @Email
    private String email;

    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public Client buildObjectFromForm() {
        return new ClientBuilder()
                .setName(getUsername())
                .setPassword(getPassword())
                .setEmail(getEmail())
                .setRole(ClientRole.valueOf(getRole()))
                .createModel();
    }

    @Override
    public void loadValuesFromInstance(Client instance) {
        setUsername(instance.getName());
        setEmail(instance.getEmail());
        setPassword(instance.getPassword());
        setRole(instance.getRole().name());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
