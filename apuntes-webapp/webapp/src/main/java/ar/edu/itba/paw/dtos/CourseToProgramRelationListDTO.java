package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.CourseProgramRelation;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class CourseToProgramRelationListDTO {
    private List<CourseToProgramRelationDTO> relationList;

    public CourseToProgramRelationListDTO() {
    }

    public CourseToProgramRelationListDTO(final List<CourseProgramRelation> relationList) {
        this.relationList = relationList.stream().map(CourseToProgramRelationDTO::new).collect(Collectors.toList());
    }

    public List<CourseToProgramRelationDTO> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<CourseToProgramRelationDTO> relationList) {
        this.relationList = relationList;
    }
}
