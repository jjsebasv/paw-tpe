package ar.edu.itba.paw.models;

public class Program {

    private final int programid;

    private final String name;
    private final String shortName;
    private final char group;

    public Program(final int programid, final String name, String shortName, char group) {
        this.programid = programid;
        this.name = name;
        this.shortName = shortName;
        this.group = group;
    }

    public int getProgramid() {
        return programid;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public char getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        return programid == program.programid;

    }

    @Override
    public int hashCode() {
        return programid;
    }
}
