package ar.edu.itba.paw.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginObjectDTO {

    private String username;
    private String password;

    public AuthenticationTokenDTO() {
    }

    public AuthenticationTokenDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
