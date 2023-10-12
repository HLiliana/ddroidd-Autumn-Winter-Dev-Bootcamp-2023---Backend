package internshipApplication.repository;
import internshipApplication.model.Applicant;

import internshipApplication.exception.BusinessException;
import internshipApplication.model.JobListing;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.TypedQuery;

import java.util.List;

public class ApplicantRepositoryImpl implements ApplicantRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emFactory;

    public ApplicantRepositoryImpl() {
        emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

        entityManager = emFactory.createEntityManager();
    }

    @Override
    public void save(Applicant applicant) throws BusinessException {
        if (entityManager == null) {
            throw new BusinessException("EntityManager is null");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(applicant);
        entityManager.getTransaction().commit();
        closeEntityManager();
    }


    @Override
    public List<Applicant> getAllApplicants() {
        TypedQuery<Applicant> typedQuery = entityManager.createQuery("SELECT a FROM Applicant a", Applicant.class);
        List<Applicant> applicantList = typedQuery.getResultList();
        closeEntityManager();
        return applicantList;
    }

    @Override
    public Applicant getApplicantById(Long id) {
        return entityManager.find(Applicant.class, id);
    }
    public void applyForJobListing(Long jobListingId, Applicant applicant) throws BusinessException{
        JobListing jobListing = entityManager.find(JobListing.class, jobListingId);
        if (jobListing != null) {
            if (applicant.getFirstName() == null || applicant.getLastName() == null || applicant.getPhone()== null
                    || applicant.getAddressLine1() == null || applicant.getAddressLine2() == null
                    || applicant.getCountry() == null || applicant.getState() == null || applicant.getCity() ==null
                    || applicant.getResume() == null || applicant.getEmail() == null) {
                throw new BusinessException("Applicant data is incomplete");
            }
            entityManager.persist(applicant);
            jobListing.getApplicants().add(applicant);
            entityManager.merge(jobListing);
        } else {
            throw new BusinessException("Job Listing not found");
        }
        entityManager.getTransaction().commit();
        closeEntityManager();
    }
    public void addApplicantsToJobListing(JobListing jobListing, List<Applicant> applicants) throws BusinessException {
        if (jobListing == null) {
            throw new BusinessException("Job Listing cannot be null");
        }

        if (applicants == null || applicants.isEmpty()) {
            throw new BusinessException("Applicants list cannot be null or empty");
        }

        for (Applicant applicant : applicants) {
            if (applicant == null) {
                throw new BusinessException("Applicant cannot be null");
            }
            applyForJobListing(jobListing.getListingID(), applicant);
        }
    }

    public void closeEntityManager() {
        EntityManagerUtils.closeEntityManager(entityManager, emFactory);
    }
}
