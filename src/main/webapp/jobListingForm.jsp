<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Job Listing Form</title>
</head>
<body>
    <h2>Add Job Listing</h2>

    <form action="JobListingServlet" method="post">
        <input type="hidden" name="fullName" value="${fullName}">

        <label for="jobTitle">Job Title:</label>
        <input type="text" name="jobTitle" required><br>

        <label for="jobDescription">Job Description:</label>
        <input type="text" name="jobDescription" required><br>

        <label for="jobRequirements">Job Requirements:</label>
        <input type="text" name="jobRequirements" required><br>

        <button type="submit">Add Job Listing</button>
    </form>
</body>
</html>
