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
@WebServlet("/homescreen")
public class HomeScreenServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to homescreen.jsp
        request.getRequestDispatcher("WEB-INF/views/homescreen.jsp").forward(request, response);
    }
}
