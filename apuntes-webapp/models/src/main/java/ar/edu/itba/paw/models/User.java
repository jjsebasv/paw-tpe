package ar.edu.itba.paw.models;

public class User {
    private String name;
    private long id;
    private String password;

    public User(String name, String password, long id) {
        this.setName(name);
        this.setId(id);
        this.setPassword(password);
    }

    private void setPassword(String password) {
        this.password = password;

    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("(%d) %s", this.id, this.name);
    }

}
