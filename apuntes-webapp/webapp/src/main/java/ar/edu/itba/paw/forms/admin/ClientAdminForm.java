package ar.edu.itba.paw.forms.admin;

import ar.edu.itba.paw.forms.ClientForm;
import ar.edu.itba.paw.interfaces.admin.IAdminForm;
import ar.edu.itba.paw.models.Client;


public class ClientAdminForm extends ClientForm implements IAdminForm<Client> {

    @Override
    public Client buildObjectFromForm() {
        return new Client(getUsername(), getPassword(), getEmail());
    }

    @Override
    public void loadValuesFromInstance(Client instance) {
        setUsername(instance.getName());
        setEmail(instance.getEmail());
        setPassword(instance.getPassword());
    }
}
