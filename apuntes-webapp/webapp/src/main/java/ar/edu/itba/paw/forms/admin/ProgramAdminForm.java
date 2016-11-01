package ar.edu.itba.paw.forms.admin;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProgramAdminForm {

    @Size(min = 6, max = 100)
    @Pattern(regexp = "[a-zA-Z0-9\\s]+")
    private String name;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "[a-z]+")
    private String shortName;

    @Size(min = 1, max = 1)
    @Pattern(regexp = "[a-z]")
    private String group;

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getGroup() {
        return group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
