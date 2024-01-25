package com.jobportal.register;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/register")
@MultipartConfig
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String confirmPassword = request.getParameter("re_pass");
        String role = request.getParameter("role");
        RequestDispatcher dispatcher = null;
        Connection con = null;

        if (password == null || password.equals("")) {
            request.setAttribute("status", "invalid");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        } else if (email == null || email.equals("")) {
            request.setAttribute("status", "invalid");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        } else if (username == null || username.equals("")) {
            request.setAttribute("status", "invalid");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        } else if (!password.equals(confirmPassword)) {
            request.setAttribute("status", "confirm_password_invalid");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root",
                        "Emebet@1994");

                PreparedStatement checkEmailStmt = con.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?");
                checkEmailStmt.setString(1, email);
                var resultSet = checkEmailStmt.executeQuery();
                resultSet.next();
                int emailCount = resultSet.getInt(1);

                if (emailCount > 0) {
                    request.setAttribute("status", "duplicate_email");
                    dispatcher = request.getRequestDispatcher("registration.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // Save file locally
                    Part filePart = request.getPart("resume");
                    String localFilePath = uploadToLocalFolder(filePart);

                    // Insert user data into the database
                    PreparedStatement pst = con.prepareStatement(
                            "INSERT INTO users(username, password, email,resume_url,role) VALUES (?, ?, ?, ?, ?)");
                    pst.setString(1, username);
                    pst.setString(2, password);
                    pst.setString(3, email);
                    pst.setString(4, localFilePath);
                    pst.setString(5, role);

                    int rowCount = pst.executeUpdate();

                    if (rowCount > 0) {
                        request.setAttribute("status", "success");
                        dispatcher = request.getRequestDispatcher("login.jsp");
                    } else {
                        request.setAttribute("status", "failed");
                        dispatcher = request.getRequestDispatcher("registration.jsp");
                    }
                    dispatcher.forward(request, response);
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
        }
    }

    // Save file locally
    private String uploadToLocalFolder(Part filePart) throws IOException {
        String fileName = getFileName(filePart);
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        // Create the uploads folder if it doesn't exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filePath = uploadPath + File.separator + fileName;

        try (InputStream fileContent = filePart.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath)) {

            // Copy the file content to the local file
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        return filePath;
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
