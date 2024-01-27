<%
if(session.getAttribute("email") == null) {
    response.sendRedirect("home.jsp");
}
%>
<%@ page import="java.util.List" %>
<%@ page import="com.jobportal.job.Job" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="all_component/all_css.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin: View Jobs</title>
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
<input type="hidden" id="status" value="<%=request.getAttribute("status")%>" >
<input type="hidden" name="userRole" value="<%= session.getAttribute("userRole") %>">

<%@ include file="all_component/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="container mt-3">
                <div class="row">
	<div class="col-md-6">
	    <h5 class="text-center text-primary"><a href="view_jobs">All Jobs</a></h5>
	</div>

                    <div class="col-md-6">
                        <form action="search" method="get" class="d-flex">
                            <div class="input-group">
                                <div class="form-outline" data-mdb-input-init>
                                    <input placeholder="search" type="search" name="search" id="form1" class="form-control"/>
                                </div>
                                <button type="submit" class="btn btn-primary btn-sm" data-mdb-ripple-init>
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <%
                List<Job> jobs = (List<Job>) request.getAttribute("jobs");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (Job job : jobs) {
            %>
            <div class="card job-card mt-3">
                <div class="card-body">
                    <div class="text-center text-primary">
                        <i class="far fa-clipboard icon"></i>
                    </div>

                    <h6 class="title"><%= job.getTitle() %></h6>
                    <p><i class="fas fa-map-marker-alt"></i> <%= job.getLocation() %></p>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <input type="text" class="form-control form-control-sm" value="Category: <%= job.getCategory() %>" readonly>
                        </div>

                        <div class="form-group col-md-4">
                            <input type="text" class="form-control form-control-sm" value="Status: <%= job.getStatus() %>" readonly>
                        </div>

                        <div class="form-group col-md-4">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class='far fa-clock'></i></span>
                                </div>
                                <input type="text" class="form-control form-control-sm time-ago" data-time="<%= job.getPostTime().getTime() %>" readonly>
                            </div>
                        </div>
                    </div>
                    <p> <%= job.getDescription() %></p>

                    <div class="text-center">
                        <% if ("employer".equals(session.getAttribute("userRole"))) { %>
                            <a href="edit_job?id=<%= job.getId() %>" class="btn btn-sm btn-success">Edit <i class="fas fa-edit"></i></a>
                            <a href="delete_job?id=<%= job.getId() %>" class="btn btn-sm btn-danger">Delete <i class="fas fa-trash-alt"></i></a>
                            <a href="job_candidates?id=<%= job.getId() %>" class="btn btn-sm btn-success">Candidates</i></a>
                        <% } else { %>
                            <% if ("Closed".equals(job.getStatus())) { %>
                                <!-- If job status is closed, disable the Apply button -->
                                <button class="btn btn-sm btn-primary" disabled>Apply <i class="fas fa-paper-plane"></i></button>
                            <% } else { %>
                                <!-- Apply button with modal for other cases -->
                                <button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#applyModal<%= job.getId() %>">Apply <i class="fas fa-paper-plane"></i></button>
                                <!-- Apply Modal -->
                                <div class="modal fade" id="applyModal<%= job.getId() %>" tabindex="-1" role="dialog" aria-labelledby="applyModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="applyModalLabel">Apply for <%= job.getTitle() %></h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <!-- Your form for phone number and cover letter -->
                                                <form action="apply_job" method="post">
                                                    <div class="form-group">
                                                        <label for="phoneNumber">Phone Number</label>
                                                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="coverLetter">Cover Letter</label>
                                                        <textarea class="form-control" id="coverLetter" name="coverLetter" rows="4" required></textarea>
                                                    </div>
                                                    <input type="hidden" name="jobId" value="<%= job.getId() %>">
                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            <% } %>
                        <% } %>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<!-- Moment.js for date manipulation -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<!-- Your custom JavaScript -->
<script>
    $(document).ready(function() {
        // Convert dates to "time ago" format
        $('.time-ago').each(function() {
            var time = $(this).data('time');
            var formattedTime = moment(time).fromNow();
            $(this).val(formattedTime);
        });
    });
</script>
<!-- Font Awesome JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
	
	
	

	


<script type="text/javascript">

var status = document.getElementById("status").value;
if(status=="success"){
	swal("congrats","your application is sent successfully","success");
}

</script>
</body>
</html>
