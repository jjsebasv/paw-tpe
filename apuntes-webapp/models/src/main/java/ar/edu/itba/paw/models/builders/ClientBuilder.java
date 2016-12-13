package ar.edu.itba.paw.models.builders;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;
import ar.edu.itba.paw.models.University;

public class ClientBuilder extends ModelBuilder<Client> {
    private String name;
    private String password;
    private String email;
    private ClientRole role;
    private University university;

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

    public ClientBuilder setUniversity(University university) {
        this.university = university;
        return this;
    }

    public Client createModel() {
        return new Client(name, password, email, role, university);
    }

}