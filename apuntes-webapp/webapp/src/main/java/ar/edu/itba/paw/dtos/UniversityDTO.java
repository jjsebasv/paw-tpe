package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.University;
import org.hibernate.validator.constraints.NotEmpty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UniversityDTO {

    private Long universityId;

    @NotEmpty
    private String name;

    private String domain;

    public UniversityDTO() {
    }

    public UniversityDTO(final University university) {
        this.universityId = university.getUniversityId();
        this.name = university.getName();
        this.domain = university.getDomain();
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
