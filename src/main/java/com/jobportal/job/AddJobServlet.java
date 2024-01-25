package com.jobportal.job;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add_job")
public class AddJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddJobServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String location = request.getParameter("location");
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        String description = request.getParameter("desc");

        HttpSession session = request.getSession();
        String user_email = (String) session.getAttribute("email");

        Connection con = null;
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root", "Emebet@1994");

            PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO jobs(title, location, category, status, description, post_time, user_email) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, title);
            pst.setString(2, location);
            pst.setString(3, category);
            pst.setString(4, status);
            pst.setString(5, description);
            pst.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            pst.setString(7, user_email);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                request.setAttribute("succMsg", "Job added successfully");
                dispatcher = request.getRequestDispatcher("add_job.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("add_job.jsp");
            }
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
