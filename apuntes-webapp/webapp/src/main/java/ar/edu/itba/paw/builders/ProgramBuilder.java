package ar.edu.itba.paw.builders;

import ar.edu.itba.paw.interfaces.ModelBuilder;
import ar.edu.itba.paw.models.Program;

public class ProgramBuilder implements ModelBuilder<Program> {
    private String name;
    private String shortName;
    private char group;

    public ProgramBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProgramBuilder setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public ProgramBuilder setGroup(char group) {
        this.group = group;
        return this;
    }

    public Program createModel() {
        return new Program(name, shortName, group);
    }
}