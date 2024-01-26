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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Connection con = null;
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;

		if (email == null || email.equals("")) {
			request.setAttribute("status", "invalid");
			dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else if (password == null || password.equals("")) {
			request.setAttribute("status", "invalid");
			dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root", "Emebet@1994");
				PreparedStatement pst = con.prepareStatement("select * from users where email=? and password=?");
				pst.setString(1, email);
				pst.setString(2, password);


				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					String userRole = rs.getString("role");
					 String name = rs.getString("username");
					session.setAttribute("email", rs.getString("email"));
					session.setAttribute("userRole", userRole);
					session.setAttribute("name", name);
					dispatcher=request.getRequestDispatcher("home.jsp");
				} else {
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("login.jsp");
				}
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();		}
		}


	}

}
