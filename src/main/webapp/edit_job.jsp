<%@ page import="com.jobportal.job.Job" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="all_component/all_css.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Jobs</title>
    <%@ include file="all_component/all_css.jsp" %>
</head>
<body style="background-color: #f0f1f2;">
    <%@ include file="all_component/navbar.jsp" %>
   
    <div class="container p-2">
        <div class="col-md-10 offset-md-1">
            <div class="card">
                <div class="card-body">
                    <div class="text-center text-success">
                        <i class="fas fa-user-friends fa-3x"></i>
                        <h5>Edit Jobs</h5>
                         <input type="hidden" id="status" value="<%=request.getAttribute("status")%>" >
                    </div>
                    <form action="update_job" method="post">
                        <%-- Using scriptlet to access the job attribute --%>
                        <% Job job = (Job) request.getAttribute("job"); %>
                        <input type="hidden" value="<%= job.getId() %>" name="id">

                        <div class="form-group">
                            <label>Enter Title</label>
                            <input type="text" name="title" required class="form-control"
                                value="<%= job.getTitle() %>">
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label>Location</label>
                                <select name="location" class="custom-select" id="inlineFormCustomSelectPref">
                                    <option value="<%= job.getLocation() %>"><%= job.getLocation() %></option>
                                    <option value="Delhi">Delhi</option>
                                    <option value="Bengaluru">Bengaluru</option>
                                    <option value="Hyderabad">Hyderabad</option>
                                    <option value="Chennai">Chennai</option>
                                    <option value="Pune">Pune</option>
                                    <option value="Noida">Noida</option>
                                    <option value="Gurgaon">Gurgaon</option>
                                </select>
                            </div>
                            <div class="form-group col-md-4">
                                <label>Category</label>
                                <select class="custom-select" id="inlineFormCustomSelectPref" name="category">
                                    <option value="<%= job.getCategory() %>"><%= job.getCategory() %></option>
                                    <option value="IT">IT</option>
                                    <option value="Developer">Developer</option>
                                    <option value="Finance">Finance</option>
                                    <option value="Engineer">Engineer</option>
                                    <option value="Business">Business</option>
                                </select>
                            </div>
                            <div class="form-group col-md-4">
                                <label>Status</label>
                                <select class="form-control" name="status">
                                    <option class="Active" value="<%= job.getStatus() %>"><%= job.getStatus() %></option>
                                    <option class="Active" value="Active">Active</option>
                                    <option class="Closed" value="Closed">Closed</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Enter Desciption</label>
                            <textarea required rows="6" cols="" name="desc" class="form-control"><%= job.getDescription() %></textarea>
                        </div>
                        <button class="btn btn-success">Update Job</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
	
	
	

	


<script type="text/javascript">

var status = document.getElementById("status").value;

if(status == "invalid"){
	swal("Sorry","Job not updated","error");
}
if(status == "success"){
	swal("Congrats","Job changed successfully","success");
}
</script>
</body>
</html>
