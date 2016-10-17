package ar.edu.itba.paw.models;

public class Client {

    private final String name;
    private final int clientId;
    private final String password;
    private final String email;

    public Client(final int clientId, final String name, final String password, String email) {
        this.name = name;
        this.clientId = clientId;
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
