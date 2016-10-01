package ar.edu.itba.paw.models;

public class Client {

    //	@Size(min = 6, max = 100)
//	@Pattern(regexp = "[a-zA-Z0-9]+")
    private final String name;
    private final int client_id;

    //	@Size(min = 6, max = 10)
    private final String password;

    public Client(final int client_id, final String name, final String password) {
        this.name = name;
        this.client_id = client_id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getClientId() {
        return client_id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("(%d) %s", this.client_id, this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return client_id == client.client_id;

    }

    @Override
    public int hashCode() {
        return client_id;
    }
}
