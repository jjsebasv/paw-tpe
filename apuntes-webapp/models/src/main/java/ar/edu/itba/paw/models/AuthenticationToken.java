package ar.edu.itba.paw.models;


import javax.persistence.*;

@Entity
@Table(name = "auth_tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_tokens_token_id_seq")
    @SequenceGenerator(sequenceName = "auth_tokens_token_id_seq", name = "auth_tokens_token_id_seq", allocationSize = 1)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(length = 50, nullable = false, unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    public AuthenticationToken() {
    }

    public AuthenticationToken(String token, Client client) {
        this.token = token;
        this.client = client;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getTokenId() {
        return tokenId;
    }
}
