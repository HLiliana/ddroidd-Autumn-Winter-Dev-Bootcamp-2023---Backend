package internshipApplication.repository;

import internshipApplication.exception.BusinessException;
import internshipApplication.model.Applicant;

import java.util.List;


public interface ApplicantRepository {
    void save(Applicant applicant) throws BusinessException;

    List<Applicant> getAllApplicants();

    Applicant getApplicantById(Long id);

}
