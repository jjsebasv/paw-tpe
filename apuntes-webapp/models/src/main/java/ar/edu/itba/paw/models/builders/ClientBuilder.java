package ar.edu.itba.paw.models.builders;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;

public class ClientBuilder extends ModelBuilder<Client> {
    private String name;
    private String password;
    private String email;
    private ClientRole role;

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder setRole(ClientRole role) {
        this.role = role;
        return this;
    }

    public Client createModel() {
        return new Client(name, password, email, role);
    }

}