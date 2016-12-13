package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ClientDTO {

    private long clientId;

    private String name;

    @XmlTransient
    private String password;

    private String email;

    private ClientRole role;

    public ClientDTO(String name, String password, String email, ClientRole role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public ClientDTO() {
    }


    public ClientDTO(final Client client) {
        this.clientId = client.getClientId();
        this.name = client.getName();
        this.password = client.getPassword();
        this.email = client.getEmail();
        this.role = client.getRole();
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientRole getRole() {
        return role;
    }

    public void setRole(ClientRole role) {
        this.role = role;
    }
}
