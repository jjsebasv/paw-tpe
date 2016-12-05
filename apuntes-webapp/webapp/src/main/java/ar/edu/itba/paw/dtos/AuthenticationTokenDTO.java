package ar.edu.itba.paw.dtos;

import ar.edu.itba.paw.models.AuthenticationToken;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticationTokenDTO {

    private String token;

    public AuthenticationTokenDTO() {
    }

    public AuthenticationTokenDTO(final AuthenticationToken authenticationToken) {
        this.token = authenticationToken.getToken();
    }

    public AuthenticationTokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
