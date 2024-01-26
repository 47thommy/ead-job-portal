<%@ page import="com.jobportal.job.Job" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="all_component/all_css.jsp" %>
<%@ page import="com.jobportal.job.Candidate" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Job Candidates</title>
    <%@ include file="all_component/all_css.jsp" %>
    <!-- Add Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Add Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>
        <!-- Your styles go here -->
    </style>
</head>
<body style="background-color: #f0f1f2;">

<%@ include file="all_component/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h5 class="text-center text-primary">Job Candidates</h5>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Email</th>
                    <th>Applied Time</th>
                    <th>Resume</th>
                </tr>
                </thead>
                <tbody>
                    <% List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
                       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                       for (Candidate candidate : candidates) { %>
                        <tr>
                            <td><%= candidate.getEmail() %></td>
                            <td><%= dateFormat.format(candidate.getAppliedTime()) %></td>
                            <td><a href="uploads/<%= candidate.getResumeUrl() %>" >View Resume</a></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

        </div>
    </div>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- Your custom JavaScript -->
<script>
    // Your custom JavaScript goes here
</script>
<!-- Font Awesome JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
</body>
</html>
