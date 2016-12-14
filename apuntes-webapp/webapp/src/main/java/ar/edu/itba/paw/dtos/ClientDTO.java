package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ClientDTO {

    private long clientId;

    private String name;

    private String email;

    private ClientRole role;

    private long universityId;

    private String recoveryQuestion;

    public ClientDTO(String name, String email, ClientRole role, long universityId, String recoveryQuestion) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.universityId = universityId;
        this.recoveryQuestion = recoveryQuestion;
    }

    public ClientDTO() {
    }

    public ClientDTO(final Client client) {
        this.clientId = client.getClientId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.role = client.getRole();
        this.universityId = client.getUniversity().getUniversityId();
        this.recoveryQuestion = client.getRecoveryQuestion();
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

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    public String getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public void setRecoveryQuestion(String recoveryQuestion) {
        this.recoveryQuestion = recoveryQuestion;
    }
}
