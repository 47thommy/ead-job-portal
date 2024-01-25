package com.jobportal.job;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/apply_job")
public class ApplyJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ApplyJobServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String coverLetter = request.getParameter("coverLetter");
        String userEmail = (String) request.getSession().getAttribute("email"); // Assuming user email is stored in the session
        int jobId = Integer.parseInt(request.getParameter("jobId"));

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root", "Emebet@1994");

            // Fetch the resume URL from the users table based on the current user's email
            String resumeUrl = getResumeUrlFromUserTable(con,userEmail);

            // Fetch the email of the person who posted the job from the jobs table based on the job id
            String jobPosterEmail = getJobPosterEmailFromJobsTable(con,jobId);

            // Insert the application details into the candidates table
            PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO candidates(user_email, employee_email, phone_number, cover_letter, resume_url,job_id, applied_time) VALUES (?, ?, ?, ?, ?, ?, NOW())");
            pst.setString(1, userEmail);
            pst.setString(2, jobPosterEmail);
            pst.setString(3, phoneNumber);
            pst.setString(4, coverLetter);
            pst.setString(5, resumeUrl);
            pst.setInt(6, jobId);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                // Application submitted successfully
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view_jobs");
                dispatcher.forward(request, response);
            } else {
                // Application submission failed
                response.sendRedirect("error.jsp"); // Redirect to an error page
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to fetch the resume URL from the users table based on the user's email
    private String getResumeUrlFromUserTable(Connection con,String userEmail) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement("SELECT resume_url FROM users WHERE email = ?")) {
            pst.setString(1, userEmail);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("resume_url");
            } else {
                return null; // Handle the case where the resume URL is not found
            }
        }
    }

    // Method to fetch the email of the person who posted the job from the jobs table based on the job id
    private String getJobPosterEmailFromJobsTable(Connection con, int jobId) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement("SELECT user_email FROM jobs WHERE id = ?")) {
            pst.setInt(1, jobId);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("user_email");
            } else {
                return null; // Handle the case where the job poster's email is not found
            }
        }
    }
}
