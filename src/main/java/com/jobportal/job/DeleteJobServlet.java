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

@WebServlet("/delete_job")
public class DeleteJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobIdString = request.getParameter("id");

        if (jobIdString != null && !jobIdString.isEmpty()) {
            int jobId = Integer.parseInt(jobIdString);

            Connection con = null;
            PreparedStatement pst = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root",
                        "Emebet@1994");

                pst = con.prepareStatement("DELETE FROM jobs WHERE id = ?");
                pst.setInt(1, jobId);

                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    // Job deleted successfully
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/view_jobs");
                    dispatcher.forward(request, response);
                } else {
                    // Handle deletion failure
                    response.sendRedirect("view_job.jsp");
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
        } else {
            // Job ID not provided, handle accordingly
            response.sendRedirect("view_job.jsp");
        }
    }
}
