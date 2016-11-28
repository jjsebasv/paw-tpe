package ar.edu.itba.paw.forms;

import ar.edu.itba.paw.models.Client;

import javax.validation.constraints.Size;

public class ChangePasswordForm {

    @Size(min = 6, max = 100)
    private String oldPassword;

    @Size(min = 6, max = 100)
    private String newPassword;

    @Size(min = 6, max = 100)
    private String repeatPassword;

    private Client client;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}