<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin: Add Jobs</title>
<%@include file="all_component/all_css.jsp"%>
</head>
<body style="background-color: #f0f1f2;">
<%--     <c:if test="${userobj.role ne 'admin' }">
        <c:redirect url="login.jsp"></c:redirect>
    </c:if> --%>
<input type="hidden" name="user_email" value="<%= session.getAttribute("email") %>">

	<%@include file="all_component/navbar.jsp"%>
	<input type="hidden" id="status" value="<%=request.getAttribute("status")%>" >
	<div class="container p-2">
		<div class="col-md-10 offset-md-1">
			<div class="card">
				<div class="card-body">
					<div class="text-center text-success">
						<i class="fas fa-user-friends fa-3x"></i>
						<c:if test="${not empty succMsg }">
							<div class="alert alert-success" role="alert">${ succMsg}</div>
							<c:remove var="succMsg" />
						</c:if>
						<h5>Add Jobs</h5>
					</div>
					<form action="add_job" method="post">
						<div class="form-group">
							<label>Enter Title</label> <input type="text" name="title"
								required class="form-control">
						</div>
						<div class="form-row">
							<div class="form-group col-md-4">
								<label>Location</label> <select name="location"
									class="custom-select" id="inlineFormCustomSelectPref">
									<option selected>Choose....</option>
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
								<label>Category</label> <select class="custom-select"
									id="inlineFormCustomSelectPref" name="category">
									<option selected>Choose....</option>
									<option value="IT">IT</option>
									<option value="Developer">Developer</option>
									<option value="Finance">Finance</option>
									<option value="Engineer">Engineer</option>
									<option value="Business">Business</option>
								</select>
							</div>
							<div class="form-group col-md-4">
								<label>Status</label> <select class="form-control" name="status">
									<option class="Active" value="Active">Active</option>
									<option class="Closed" value="Closed">Closed</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label>Enter Desciption</label>
							<textarea required rows="6" cols="" name="desc"
								class="form-control"></textarea>
						</div>
						<button class="btn btn-success">Publish Job</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	
	
	
	

	


<script type="text/javascript">

var status = document.getElementById("status").value;
if(status=="Job added successfully"){
	swal("Congrats","Job added successfully","success");
}
if(status=="failed"){
	swal("Sorry","Job could not be created","error");
}
</script>
</body>
</html>