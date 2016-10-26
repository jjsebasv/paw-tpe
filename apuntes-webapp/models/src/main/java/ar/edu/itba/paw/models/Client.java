package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_client_id_seq")
    @SequenceGenerator(sequenceName = "clients_client_id_seq", name = "clients_client_id_seq", allocationSize = 1)
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "username", length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    /* package */ Client() {
        // Just for Hibernate, we love you!
    }

    public Client(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getClientId() {
        return clientId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("(%d) %s", this.clientId, this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return clientId == client.clientId;

    }

    @Override
    public int hashCode() {
        return clientId;
    }

    public String getEmail() {
        return email;
    }
}
