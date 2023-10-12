package internshipApplication.model;


import javax.persistence.*;
import jakarta.persistence.Persistence;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "employers")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employer {

    private Long employerId;
    private String name;

    @OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "employers_jobs_listing",
            joinColumns = { @JoinColumn(name = "employers_employer_id") },
            inverseJoinColumns = { @JoinColumn(name = "jobs_listing_listing_id") }
    )
    @Fetch(FetchMode.JOIN)
    private List<JobListing> jobListings;

    @OneToMany(mappedBy = "employer")
    private List<Applicant> applicantList;
    public Employer(String name, List<JobListing> jobListings) {
        this.name = name;
        this.jobListings = jobListings;
    }
}
