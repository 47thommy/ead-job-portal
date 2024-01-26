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

@WebServlet("/edit_job")
public class EditJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditJobServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobIdString = request.getParameter("id");

        if (jobIdString != null && !jobIdString.isEmpty()) {
            int jobId = Integer.parseInt(jobIdString);

            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root",
                        "Emebet@1994");

                pst = con.prepareStatement("SELECT * FROM jobs WHERE id = ?");
                pst.setInt(1, jobId);
                rs = pst.executeQuery();

                if (rs.next()) {
                    Job job = new Job();
                    job.setId(rs.getInt("id"));
                    job.setTitle(rs.getString("title"));
                    job.setLocation(rs.getString("location"));
                    job.setCategory(rs.getString("category"));
                    job.setStatus(rs.getString("status"));
                    job.setDescription(rs.getString("description"));

                    request.setAttribute("job", job);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("edit_job.jsp");
                    String status = (String) request.getAttribute("status");
                    if (status != null) {
                        request.setAttribute("status", status);
                    }
                    dispatcher.forward(request, response);
                } else {
                    // Job not found, handle accordingly
                    response.sendRedirect("view_job.jsp");
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
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
            
            response.sendRedirect("view_job.jsp");
        }
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobIdString = request.getParameter("id");

        if (jobIdString != null && !jobIdString.isEmpty()) {
            int jobId = Integer.parseInt(jobIdString);

            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root",
                        "Emebet@1994");

                pst = con.prepareStatement("SELECT * FROM jobs WHERE id = ?");
                pst.setInt(1, jobId);
                rs = pst.executeQuery();

                if (rs.next()) {
                    Job job = new Job();
                    job.setId(rs.getInt("id"));
                    job.setTitle(rs.getString("title"));
                    job.setLocation(rs.getString("location"));
                    job.setCategory(rs.getString("category"));
                    job.setStatus(rs.getString("status"));
                    job.setDescription(rs.getString("description"));

                    request.setAttribute("job", job);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("edit_job.jsp");
                    String status = (String) request.getAttribute("status");
                    if (status != null) {
                        request.setAttribute("status", status);
                    }
                    dispatcher.forward(request, response);
                } else {
                    // Job not found, handle accordingly
                    response.sendRedirect("view_job.jsp");
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
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
            
            response.sendRedirect("view_job.jsp");
        }
    }
}
