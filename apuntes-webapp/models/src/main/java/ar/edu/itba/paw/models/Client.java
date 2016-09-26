package ar.edu.itba.paw.models;

public class Client {

//	@Size(min = 6, max = 100)
//	@Pattern(regexp = "[a-zA-Z0-9]+")
    private final String name;
    private final int userid;
    
//	@Size(min = 6, max = 10)
    private final String password;

    public Client(final int userid, final String name, final String password) {
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
