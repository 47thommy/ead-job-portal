<%
if(session.getAttribute("email") == null) {
    response.sendRedirect("home.jsp");
}
%>
<%@ page import="com.jobportal.job.Candidate" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="all_component/all_css.jsp" %>

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
                    <th>Phone number</th>
                    <th>Applied Time</th>
                    <!-- <th>Cover Letter</th> -->
                    <th>Resume</th>
                </tr>
                </thead>
                <tbody>
                    <% List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidates");
                       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                       
                       for (Candidate candidate : candidates) { 
                    	     String coverletter = candidate.getCover();
                    	   
                       %>
                       <p> 
                       
                       </p>
                        <tr>
                            <td><%= candidate.getEmail() %></td>
                            <td><%= candidate.getPhone() %></td>
                            <td><%= dateFormat.format(candidate.getAppliedTime()) %></td>
                           <%--  <td><a href="#" onclick="openCoverLetter('<%= coverletter %>')">Cover Letter</a></td> --%>
                            <td><a href="uploads/<%= candidate.getResumeUrl() %>" target="_blank">View Resume</a></td>
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
    function openCoverLetter(coverContent) {
        // Replace newlines with HTML line breaks
        coverContent = coverContent.replace(/\n/g, "<br>");
        // Display the cover letter content in a modal
        $('#coverLetterModalBody').html(coverContent);
        $('#coverLetterModal').modal('show');
    }
</script>
<!-- Font Awesome JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>

<!-- Modal for Cover Letter -->
<div class="modal fade" id="coverLetterModal" tabindex="-1" role="dialog" aria-labelledby="coverLetterModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="coverLetterModalLabel">Cover Letters</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="coverLetterModalBody">
                <!-- Cover letter content will be inserted here -->
            </div>
        </div>
    </div>
</div>

</body>
</html>
