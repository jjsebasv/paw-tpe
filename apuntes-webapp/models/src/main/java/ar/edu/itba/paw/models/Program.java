package ar.edu.itba.paw.models;

public class Program {

    private final int programid;

    private final String name;

    public Program(final int programid,final String name) {
        this.programid = programid;
        this.name = name;
    }

    public int getProgramid() {
        return programid;
    }

    public String getName() {
        return name;
    }
}
