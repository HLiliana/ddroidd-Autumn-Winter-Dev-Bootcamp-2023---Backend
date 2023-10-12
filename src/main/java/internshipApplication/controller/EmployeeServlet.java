package internshipApplication.controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        String fullName = firstName + " " + lastName;
        /**
         * Since in the requirement I did not find how the employers are added, below I have a list of two employees.
         */
        List<String> employees = Arrays.asList("Pop Maria", "Rus Ioana");
        if (employees.contains(fullName)) {
            request.setAttribute("fullName", fullName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jobListingForm.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("employeeNotFound.jsp");
        }
    }
}
