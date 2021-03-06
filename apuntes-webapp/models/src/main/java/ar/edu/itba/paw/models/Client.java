package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_client_id_seq")
    @SequenceGenerator(sequenceName = "clients_client_id_seq", name = "clients_client_id_seq", allocationSize = 1)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "username", length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "recovery_question", length = 300)
    private String recoveryQuestion;

    @Column(name = "secret_answer", length = 300)
    private String secretAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "program_id")
    private Program program;

    @Column
    @Enumerated(EnumType.STRING)
    private ClientRole role;

    /* package */ Client() {
        // Just for Hibernate, we love you!
    }

    public Client(String name, String password, String email, ClientRole role, University university, String recoveryQuestion, String secretAnswer, Program program) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.university = university;
        this.recoveryQuestion = recoveryQuestion;
        this.secretAnswer = secretAnswer;
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public long getClientId() {
        return clientId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(ClientRole role) {
        this.role = role;
    }

    public ClientRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.format("(%d) %s", this.clientId, this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return clientId.equals(client.clientId);
    }

    @Override
    public int hashCode() {
        return clientId.hashCode();
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public void setRecoveryQuestion(String recoveryQuestion) {
        this.recoveryQuestion = recoveryQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer != null ? secretAnswer : "";
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public boolean isAdmin() {
        return this.role == ClientRole.ROLE_ADMIN;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
