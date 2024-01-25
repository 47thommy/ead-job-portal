<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index.jsp">Job Portal</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <%-- Check if the user is an employer to display "post jobs" link --%>
            <%
                String userRole = (String) session.getAttribute("userRole");
                if ("employer".equals(userRole)) {
            %>
                    <li class="nav-item">
                        <a class="nav-link" href="add_job.jsp">Post Jobs</a>
                    </li>
                                <li class="nav-item">
                <a class="nav-link" href="view_jobs">View Jobs</a>
            </li>
                    
            <%
                }
            %>
            <li class="nav-item">
                <a class="nav-link text-danger" href="logout">Logout</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-danger" href="#"><%=session.getAttribute("email") %></a>
            </li>
        </ul>
    </div>
</nav>
