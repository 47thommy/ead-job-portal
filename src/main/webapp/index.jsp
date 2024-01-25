<%
if(session.getAttribute("email") == null) {
    response.sendRedirect("login.jsp");
} else {
    String userRolee = (String) session.getAttribute("userRole");

    if ("employer".equals(userRolee)) {
%>
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="" />
            <meta name="author" content="" />
            <title>Job Portal</title>

            <!-- Bootstrap CSS -->
            <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        </head>
        <body>

        <%@include file="all_component/navbar.jsp" %>

        <!-- Content for employer -->
        <div class="container mt-3">
            <h1>Welcome, Admin!</h1>
            <!-- Your content for employers goes here -->
        </div>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        </body>
        </html>
<%
    } else {
        response.sendRedirect("view_jobs");
    }
}
%>
