package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class DocumentListDTO {

    private List<DocumentDTO> documentList;

    public DocumentListDTO() {
    }

    public DocumentListDTO(final List<Document> documentList) {
        if (documentList == null) {
            this.documentList = new ArrayList<>();
        } else {
            this.documentList = documentList.stream().map(DocumentDTO::new).collect(Collectors.toList());
        }
    }

    public List<DocumentDTO> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<DocumentDTO> documentList) {
        this.documentList = documentList;
    }
}
