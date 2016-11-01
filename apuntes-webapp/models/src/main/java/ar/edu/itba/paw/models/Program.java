package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "programs_program_id_seq")
    @SequenceGenerator(sequenceName = "programs_program_id_seq", name = "programs_program_id_seq", allocationSize = 1)
    @Column(name = "program_id")
    private int programid;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "short_name", length = 50, nullable = false, unique = true)
    private String shortName;

    @Column(name = "program_group", length = 1, nullable = false)
    private char group;

    @OneToMany(mappedBy = "program", fetch = FetchType.EAGER)
    private List<CourseProgramRelation> courses;

    /* package */ Program() {
        // Just for Hibernate, we love you!
    }

    public Program(final String name, String shortName, char group) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setGroup(char group) {
        this.group = group;
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

    public List<CourseProgramRelation> getRelatedCourses() {
        return courses;
    }
}
