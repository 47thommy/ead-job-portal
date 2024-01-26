package com.jobportal.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        HttpSession session = request.getSession();
        String user_email = (String) session.getAttribute("email");
        Connection con = null;
        RequestDispatcher dispatcher = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root",
                    "Emebet@1994");

            // Check if old password matches the one in the database
            if (oldPassword != null && !oldPassword.isEmpty()) {
                PreparedStatement checkPasswordStmt = con
                        .prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
                checkPasswordStmt.setString(1, user_email);
                checkPasswordStmt.setString(2, oldPassword);
                if (!checkPasswordStmt.executeQuery().next()) {
                    // Incorrect old password, handle accordingly (e.g., redirect with an error message)
                    request.setAttribute("status", "failed");
                    dispatcher = request.getRequestDispatcher("profile.jsp");
                } else {
                    // Update password only if oldPassword is provided
                    PreparedStatement updatePasswordStmt = con
                            .prepareStatement("UPDATE users SET password=? WHERE email=?");
                    updatePasswordStmt.setString(1, newPassword);
                    updatePasswordStmt.setString(2, user_email);
                    updatePasswordStmt.executeUpdate();
                    request.setAttribute("status", "resetSuccess");
                    // Fetch the updated user details before redirecting
                    PreparedStatement selectUserStmt = con
                            .prepareStatement("SELECT username, email FROM users WHERE email=?");
                    selectUserStmt.setString(1, user_email);
                    ResultSet resultSet = selectUserStmt.executeQuery();
                    if (resultSet.next()) {
                        // Update session attributes with the latest details
                        session.setAttribute("email", resultSet.getString("email"));
                        session.setAttribute("name", resultSet.getString("username"));
                    }
                    dispatcher = request.getRequestDispatcher("profile.jsp");
                }
            } else {
                // Update user profile
                PreparedStatement updateProfileStmt = con
                        .prepareStatement("UPDATE users SET username=? WHERE email=?");
                updateProfileStmt.setString(1, name);
                updateProfileStmt.setString(2, user_email);
                updateProfileStmt.executeUpdate();
                // Fetch the updated user details before redirecting
                PreparedStatement selectUserStmt = con
                        .prepareStatement("SELECT username, email FROM users WHERE email=?");
                selectUserStmt.setString(1, user_email);
                ResultSet resultSet = selectUserStmt.executeQuery();
                if (resultSet.next()) {
                    // Update session attributes with the latest details
                    session.setAttribute("email", resultSet.getString("email"));
                    session.setAttribute("name", resultSet.getString("username"));
                }
                request.setAttribute("status", "resetSuccess");
                dispatcher = request.getRequestDispatcher("profile.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions accordingly (e.g., redirect with an error message)
            response.sendRedirect("edit_profile.jsp?error=DatabaseError");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Perform the redirection outside the try block
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}
