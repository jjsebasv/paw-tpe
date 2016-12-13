package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "universities_university_id_seq")
    @SequenceGenerator(sequenceName = "universities_university_id_seq", name = "universities_university_id_seq", allocationSize = 1)
    @Column(name = "university_id")
    private Long universityId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String domain;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "university_id")
    private List<Program> programs;

    /* package */ University() {
        // Just for Hibernate, we love you!
    }

    public University(final String name, final String domain) {
        this.name = name;
        this.domain = domain;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
