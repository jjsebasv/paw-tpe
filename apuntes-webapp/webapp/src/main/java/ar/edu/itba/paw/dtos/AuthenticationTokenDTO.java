package ar.edu.itba.paw.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthenticationTokenDTO {

    private String token;

    public AuthenticationTokenDTO() {
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
