package ar.edu.itba.paw.models.builders;

import ar.edu.itba.paw.models.University;

public class UniversityBuilder extends ModelBuilder<University> {
    private String name;

    public UniversityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public University createModel() {
        return new University(name);
    }
}