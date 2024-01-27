<%
if(session.getAttribute("email") == null) {
    response.sendRedirect("home.jsp");
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit profile page</title>
<%@include file="all_component/all_css.jsp"%>
</head>
<body style="background-color: #f0f1f2;">

	<%@include file="all_component/navbar.jsp"%>
	<div class="container-fluid">
		<div class="row p-4">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body">
						<div class="text-center">
							<i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
							<h5>Edit Profile</h5>
						</div>

						<form action="update_profile" method="post">

							<input type="hidden" name="id" value="${userobj.id }">
							<div class="form-group">
								<label>Enter Full Name</label> <input type="text"
									 class="form-control"
									id="exampleInputEmail1" aria-describedby="emailHelp"
									name="name" value="<%=session.getAttribute("name") %>">
							</div>

														<div class="form-group">
								<label for="exampleInputPassword1">Enter old Password</label> <input
									 type="password" class="form-control"
									id="exampleInputPassword1" name="oldPassword"
									>
							</div>

							<div class="form-group">
								<label for="exampleInputPassword1">Enter Password</label> <input
									 type="password" class="form-control"
									id="exampleInputPassword1" name="newPassword"
									>
							</div>
							<button type="submit"
								class="btn btn-primary badge-pill btn-block">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>