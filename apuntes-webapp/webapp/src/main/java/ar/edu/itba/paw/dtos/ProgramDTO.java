package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Program;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProgramDTO {

    private long programid;

    private long universityId;

    private String name;

    private String shortName;

    private char group;

    public ProgramDTO() {
    }

    public ProgramDTO(final Program program) {
        this.programid = program.getProgramid();
        this.name = program.getName();
        this.shortName = program.getShortName();
        this.group = program.getGroup();
        this.universityId = program.getUniversity().getUniversityId();
    }

    public Long getProgramid() {
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

    public void setProgramid(Long programid) {
        this.programid = programid;
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

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }
}
