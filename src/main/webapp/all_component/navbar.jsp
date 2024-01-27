<%
    String email = (String) session.getAttribute("email");
    String userRole = (String) session.getAttribute("userRole");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home.jsp">Job Portal</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <%-- Check if the user is logged in --%>
            <% if (email == null) { %>
                <li class="nav-item">
                    <a class=" fs-6 nav-link  text-dark" href="login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a  class="nav-link text-dark items" href="registration.jsp">Signup</a>
                </li>
            <% } else { %>
                <%-- Check if the user is an employer to display "post jobs" link --%>
                <% if ("employer".equals(userRole)) { %>
                    <li class="nav-item">
                        <a class="nav-link items text-dark" href="add_job.jsp">Post Jobs</a>
                    </li>
                <% } %>
                                    <li class="nav-item">
                        <a class="nav-link items text-dark" href="view_jobs">View Jobs</a>
                    </li>

                <li class="nav-item">
                    <a class="nav-link items text-dark" href="profile.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark " href="#"><%=session.getAttribute("email") %></a>
                </li>
                                <li class="nav-item">
                    <a class="nav-link text-danger items" href="logout">Logout</a>
                </li>
            <% } %>
        </ul>
    </div>
</nav>
