package internshipApplication.controller;
import internshipApplication.exception.BusinessException;
import internshipApplication.model.Employer;
import internshipApplication.model.JobListing;
import internshipApplication.repository.EmployerRepositoryImpl;
import internshipApplication.repository.JobListingRepositoryImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/JobListingServlet")
public class JobListingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        EmployerRepositoryImpl employerRepositoryImpl = new EmployerRepositoryImpl();
        Employer employer;
        try {
            employer = employerRepositoryImpl.findEmployerByName(firstName + " " + lastName);
        } catch (BusinessException e) {
            throw new ServletException("Employer can not be found in database.");
        }

        if (employer != null) {
            String jobTitle = request.getParameter("jobTitle");
            String jobRequirements = request.getParameter("jobRequirements");
            String jobDescription = request.getParameter("jobDescription");

            JobListingRepositoryImpl jobListingRepository = new JobListingRepositoryImpl();

            JobListing jobListing = new JobListing();
            jobListing.setTitle(jobTitle);
            jobListing.setRequirements(jobRequirements);
            jobListing.setDescription(jobDescription);

            try {
                jobListingRepository.save(jobListing);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
