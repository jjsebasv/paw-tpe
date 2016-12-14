package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Client;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class ClientListDTO {
    private List<ClientDTO> clientList;

    public ClientListDTO() {
    }

    public ClientListDTO(final List<Client> clientList) {
        this.clientList = clientList.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
    }
}
