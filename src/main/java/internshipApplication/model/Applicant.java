package internshipApplication.model;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "applicants")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {
    @Id
    @Column(name = "applicant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantID;
    private String firstName;
    private String lastName;
    private String name = firstName.concat(" ").concat(lastName);
    private String phone;
    private String email;
    /**
     * In the initial requirements the applicant does not seem to have all this information.
     * I decided to follow the design received, where all fields are mandatory to complete.
     * As a constructor I use one just with 4 variables to be used in the relation with database.
     * Only this for will be saved.
     * The rest of them can be used to check further in the development of the application other
     * business requirements.
     */
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String state;
    private String city;
    private String resume;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "applicants_jobs_listing",
            joinColumns = @JoinColumn(name = "applicants_applicant_id"),
            inverseJoinColumns = @JoinColumn(name = "jobs_listing_listing_id")
    )
    @Fetch(FetchMode.JOIN)
    private List<JobListing> appliedListings;
    public Applicant(String name, String email, String phone, String resume) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.resume = resume;
    }

}
