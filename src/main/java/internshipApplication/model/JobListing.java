package internshipApplication.model;


import javax.persistence.*;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "jobs_listing")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobListing {
    @Id
    @Column(name = "listing_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingID;
    private String title;
    private String description;
    private String requirements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employers_employer_id")
    private Employer employer;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "jobs_listing_applicants",
            joinColumns = {@JoinColumn(name = "listing_id")},
            inverseJoinColumns = {@JoinColumn(name = "applicant_id")}
    )
    @Fetch(FetchMode.JOIN)
    private List<Applicant> applicants;

    public JobListing(String title, String description, String requirements, Employer employer, List<Applicant> applicants) {
        this.title = title;
        this.description = description;
        this.employer=employer;
        this.requirements = requirements;
        this.applicants = applicants;
    }
}
