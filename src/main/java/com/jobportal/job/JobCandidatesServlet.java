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

@WebServlet("/job_candidates")
public class JobCandidatesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JobCandidatesServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int jobId = Integer.parseInt(request.getParameter("id"));


        List<Candidate> candidates = getCandidatesForJob(jobId);


        request.setAttribute("candidates", candidates);



        RequestDispatcher dispatcher = request.getRequestDispatcher("/candidates.jsp");
        dispatcher.forward(request, response);
    }

    private List<Candidate> getCandidatesForJob(int jobId) {
        List<Candidate> candidates = new ArrayList<>();
        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?useSSL=false", "root", "Emebet@1994");


            String sql = "SELECT user_email, applied_time, resume_url, phone_number,cover_letter FROM candidates WHERE job_id = ?";

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, jobId);
                ResultSet resultSet = pst.executeQuery();

                while (resultSet.next()) {
                    String email = resultSet.getString("user_email");
                    Timestamp appliedTime = resultSet.getTimestamp("applied_time");
                    String resumeUrl = resultSet.getString("resume_url");
                    String phone = resultSet.getString("phone_number");
                    String cover = resultSet.getString("cover_letter");


                    Candidate candidate = new Candidate(email, appliedTime, resumeUrl ,phone,cover);
                    System.out.println(candidate.getCover());
                    candidates.add(candidate);
                }
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

        return candidates;
    }
}
