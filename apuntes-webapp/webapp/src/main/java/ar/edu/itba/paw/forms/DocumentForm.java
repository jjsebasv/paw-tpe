package ar.edu.itba.paw.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DocumentForm {

    @Size(min = 6, max = 100)
    @Pattern(regexp = "[\\s\\w]+")
    private String subject;

    @NotNull
    private Integer courseid;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }
}
