package com.jobportal.job;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update_job")
public class UpdateJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int jobId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String location = request.getParameter("location");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        String description = request.getParameter("desc");

        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root",
                    "Emebet@1994");

            pst = con.prepareStatement(
                    "UPDATE jobs SET title=?, location=?, category=?, status=?, description=? WHERE id=?");
            pst.setString(1, title);
            pst.setString(2, location);
            pst.setString(3, category);
            pst.setString(4, status);
            pst.setString(5, description);
            pst.setInt(6, jobId);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                // Job updated successfully
                // Forward the request to ViewJobsServlet
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view_jobs");
                dispatcher.forward(request, response);
            } else {
                // Handle update failure
                response.sendRedirect("edit_job.jsp?id=" + jobId);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exceptions
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
}
