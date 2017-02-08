package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Program;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class ProgramListDTO {

    private List<ProgramDTO> programList;

    public ProgramListDTO() {
    }

    public ProgramListDTO(final List<Program> programList) {
        this.programList = programList.stream().map(ProgramDTO::new).collect(Collectors.toList());
    }

    public List<ProgramDTO> getProgramList() {
        return programList;
    }

    public void setProgramList(List<ProgramDTO> programList) {
        this.programList = programList;
    }
}
