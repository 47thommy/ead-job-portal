package com.jobportal.job;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view_jobs")
public class ViewJobsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewJobsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Job> jobs = new ArrayList<>();
        Connection con = null;
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root", "Emebet@1994");

            String userEmail = (String) request.getSession().getAttribute("email");
            String userRole = (String) request.getSession().getAttribute("userRole");
            String userRole2 = (String) request.getAttribute("userRole");
            System.out.println(userRole);
            System.out.println(userRole2);
            if ("employer".equals(userRole) || "employer".equals(userRole2)) {

                PreparedStatement pst = con.prepareStatement("SELECT * FROM jobs WHERE user_email = ?");
                pst.setString(1, userEmail);
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    String category = resultSet.getString("category");
                    String status = resultSet.getString("status");
                    Timestamp postTime = resultSet.getTimestamp("post_time");

                    Job job = new Job(id, title, description, location, category, status, postTime);
                    jobs.add(job);
                }
            } else {

                PreparedStatement pst = con.prepareStatement("SELECT * FROM jobs");
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    String category = resultSet.getString("category");
                    String status = resultSet.getString("status");
                    Timestamp postTime = resultSet.getTimestamp("post_time");

                    Job job = new Job(id, title, description, location, category, status, postTime);
                    jobs.add(job);
                }
            }

            request.setAttribute("jobs", jobs);
            dispatcher = request.getRequestDispatcher("view_job.jsp");
            dispatcher.forward(request, response);

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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Job> jobs = new ArrayList<>();
        Connection con = null;
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root", "Emebet@1994");


            String userEmail = (String) request.getSession().getAttribute("email");
            String userRole = (String) request.getSession().getAttribute("role");

            if ("employer".equals(userRole)) {

                PreparedStatement pst = con.prepareStatement("SELECT * FROM jobs WHERE user_email = ?");
                pst.setString(1, userEmail);
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    String category = resultSet.getString("category");
                    String status = resultSet.getString("status");
                    Timestamp postTime = resultSet.getTimestamp("post_time");

                    Job job = new Job(id, title, description, location, category, status, postTime);
                    jobs.add(job);
                }
            } else {

                PreparedStatement pst = con.prepareStatement("SELECT * FROM jobs");
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    String location = resultSet.getString("location");
                    String category = resultSet.getString("category");
                    String status = resultSet.getString("status");
                    Timestamp postTime = resultSet.getTimestamp("post_time");

                    Job job = new Job(id, title, description, location, category, status, postTime);
                    jobs.add(job);
                }
            }

            request.setAttribute("jobs", jobs);
            dispatcher = request.getRequestDispatcher("view_job.jsp");
            dispatcher.forward(request, response);

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
    }

}
