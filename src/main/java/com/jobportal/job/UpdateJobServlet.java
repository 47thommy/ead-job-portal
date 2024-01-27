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
import jakarta.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();

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
            	request.setAttribute("userRole", "employer");
            	request.setAttribute("status", "success");



            } else {
            	request.setAttribute("status", "invalid");


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
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit_job?id=" + jobId);
        dispatcher.forward(request, response);
    }
}
