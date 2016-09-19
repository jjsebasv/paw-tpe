package ar.edu.itba.paw.models;

public class User {

    private final String name;
    private final int userid;
    private final String password;

    public User(final int userid, final String name, final String password) {
        this.name = name;
        this.userid = userid;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("(%d) %s", this.userid, this.name);
    }

}
