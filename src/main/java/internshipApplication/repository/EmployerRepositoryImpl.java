package internshipApplication.repository;

import internshipApplication.exception.BusinessException;
import internshipApplication.model.Applicant;
import internshipApplication.model.Employer;
import internshipApplication.model.JobListing;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployerRepositoryImpl implements EmployerRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emFactory;

    public EmployerRepositoryImpl() {
        emFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

        entityManager = emFactory.createEntityManager();
    }

    @Override
    public void save(Employer employer) throws BusinessException {
        if (entityManager == null) {
            throw new BusinessException("EntityManager is null");
        }
        entityManager.getTransaction().begin();
        entityManager.persist(employer);
        entityManager.getTransaction().commit();
        closeEntityManager();
    }

    @Override
    public List<Employer> getAllEmployers() {
        TypedQuery<Employer> typedQuery = entityManager.createQuery("SELECT e FROM Employer e", Employer.class);
        List<Employer> employerList = typedQuery.getResultList();
        closeEntityManager();
        return employerList;
    }

    @Override
    public Employer getEmployerById(Long employerId) {
        return entityManager.find(Employer.class, employerId);
    }

    @Override
    public void addJobListing(Long employerId, JobListing jobListing) throws BusinessException {
        entityManager.getTransaction().begin();
        Employer employer = getEmployerById(employerId);
        if (employer == null) {
            throw new BusinessException("Employer not found");
        }

        if (jobListing == null) {
            throw new BusinessException("Job listing cannot be null");
        }

        if (jobListing.getTitle() == null) {
            throw new BusinessException("Title cannot be null");
        }

        if (jobListing.getDescription() == null) {
            throw new BusinessException("Description cannot be null");
        }

        if (jobListing.getRequirements() == null) {
            throw new BusinessException("Requirements cannot be null");
        }

        employer.getJobListings().add(jobListing);
        jobListing.setEmployer(employer);
        entityManager.merge(employer);
        entityManager.getTransaction().commit();
        closeEntityManager();
    }

    @Override
    public Employer findEmployerByName(String employerName) throws BusinessException {
        entityManager.getTransaction().begin();
        TypedQuery<Employer> query = entityManager.createQuery("SELECT e FROM Employer e WHERE e.name = :fullName", Employer.class);
        query.setParameter("employerName", employerName);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new BusinessException("Employer not found");
        } finally {
            closeEntityManager();
        }
    }

    @Override
    public List<Applicant> getAllApplicantsForEmployee(Long employerId) {
        TypedQuery<Applicant> query = entityManager.createQuery(
                "SELECT a FROM Applicant a WHERE a.employer_id = :employerId", Applicant.class);
        query.setParameter("employerId", employerId);
        return query.getResultList();
    }

    @Override
    public List<JobListing> getJobListingsForEmployer(Long employerId) throws BusinessException {
        Employer employer = getEmployerById(employerId);
        if (employer != null) {
            return employer.getJobListings();
        } else {
            throw new BusinessException("Employer was not found");
        }
    }

    public void closeEntityManager() {
        EntityManagerUtils.closeEntityManager(entityManager, emFactory);
    }
}




