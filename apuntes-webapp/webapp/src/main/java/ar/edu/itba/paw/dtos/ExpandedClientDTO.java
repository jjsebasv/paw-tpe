package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.Client;
import ar.edu.itba.paw.models.ClientRole;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExpandedClientDTO extends ClientDTO {

    private String password;

    private String secretAnswer;

    public ExpandedClientDTO(String name, String password, String email, ClientRole role, long universityId, String recoveryQuestion, String secretAnswer, long programId) {
        super(name, email, role, universityId, recoveryQuestion, programId);
        this.password = password;
        this.secretAnswer = secretAnswer;
    }

    public ExpandedClientDTO() {
    }

    public ExpandedClientDTO(final Client client) {
        super(client);
        this.password = client.getPassword();
        this.secretAnswer = client.getSecretAnswer();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }
}
