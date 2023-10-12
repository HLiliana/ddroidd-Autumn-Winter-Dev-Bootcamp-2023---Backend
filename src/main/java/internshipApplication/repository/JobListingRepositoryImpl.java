package internshipApplication.repository;

import internshipApplication.exception.BusinessException;
import internshipApplication.model.Applicant;
import internshipApplication.model.JobListing;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.TypedQuery;

import java.util.List;

public class JobListingRepositoryImpl implements JobListingRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emFactory;

    public JobListingRepositoryImpl() {
        emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

        entityManager = emFactory.createEntityManager();
    }

    @Override
    public void save(JobListing jobListing) throws BusinessException {
        if (entityManager == null) {
            throw new BusinessException("EntityManager is null");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(jobListing);
        entityManager.getTransaction().commit();
        closeEntityManager();
    }

    @Override
    public List<JobListing> getAllJobListings() {
        TypedQuery<JobListing> typedQuery = entityManager.createQuery("SELECT j FROM JobListing j", JobListing.class);
        List<JobListing> jobListingList = typedQuery.getResultList();
        closeEntityManager();
        return jobListingList;
    }

    @Override
    public JobListing getJobListingById(Long listingId) {
        return entityManager.find(JobListing.class, listingId);
    }

    @Override
    public List<Applicant> getAllApplicantsForJobListing(Long jobListingId) throws BusinessException {
        JobListing jobListing = getJobListingById(jobListingId);
        if (jobListing != null) {
            return jobListing.getApplicants();
        } else {
            throw new BusinessException("This job listing has no applicants.");
        }
    }
    @Override
    public void deleteJobListing(Long jobListingId) throws BusinessException {
        entityManager.getTransaction().begin();
        JobListing jobListing = getJobListingById(jobListingId);
        if (jobListing != null) {
            entityManager.remove(jobListing);
        } else {
            throw new BusinessException("Job Listing not found");
        }
        entityManager.getTransaction().commit();
        closeEntityManager();
    }

    public void closeEntityManager() {
        EntityManagerUtils.closeEntityManager(entityManager, emFactory);
    }
}