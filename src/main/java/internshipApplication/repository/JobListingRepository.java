package internshipApplication.repository;

import internshipApplication.exception.BusinessException;
import internshipApplication.model.Applicant;
import internshipApplication.model.JobListing;

import java.util.List;

public interface JobListingRepository {
    void save(JobListing jobListing) throws BusinessException;

    List<JobListing> getAllJobListings();

    JobListing getJobListingById(Long id);
    public List<Applicant> getAllApplicantsForJobListing(Long jobListingId) throws BusinessException;

    void deleteJobListing(Long jobListingId) throws BusinessException;
}
