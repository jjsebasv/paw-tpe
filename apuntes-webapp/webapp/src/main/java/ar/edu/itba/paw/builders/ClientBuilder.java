package ar.edu.itba.paw.builders;

import ar.edu.itba.paw.interfaces.ModelBuilder;
import ar.edu.itba.paw.models.Client;

public class ClientBuilder implements ModelBuilder<Client> {
    private String name;
    private String password;
    private String email;

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

    public Client createModel() {
        return new Client(name, password, email);
    }
}