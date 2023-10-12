package internshipApplication.repository;

import internshipApplication.exception.BusinessException;
import internshipApplication.model.Applicant;
import internshipApplication.model.Employer;
import internshipApplication.model.JobListing;

import java.util.List;

public interface EmployerRepository {
    void save(Employer employer) throws BusinessException;
    List<Employer> getAllEmployers();
    Employer getEmployerById(Long id);
    void addJobListing(Long employerId, JobListing jobListing) throws BusinessException;
    Employer findEmployerByName(String employerName) throws BusinessException;
    List<Applicant> getAllApplicantsForEmployee(Long employerId);

    List<JobListing> getJobListingsForEmployer(Long employerId) throws BusinessException;
}

