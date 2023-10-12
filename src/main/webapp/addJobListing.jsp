<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form action="/addJobListing" method="post" modelAttribute="jobListing">
    <form:input path="title" placeholder="Title" /><br/>
    <form:input path="description" placeholder="Description" /><br/>
    <form:input path="requirements" placeholder="Requirements" /><br/>
    <input type="submit" value="Add Job Listing" />
</form:form>