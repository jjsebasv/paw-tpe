package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.University;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class UniversityListDTO {
    private List<UniversityDTO> universityList;

    public UniversityListDTO() {
    }

    public UniversityListDTO(final List<University> universityList) {
        this.universityList = universityList.stream().map(UniversityDTO::new).collect(Collectors.toList());
    }

    public List<UniversityDTO> getUniversityList() {
        return universityList;
    }

    public void setUniversityList(List<UniversityDTO> universityList) {
        this.universityList = universityList;
    }
}
