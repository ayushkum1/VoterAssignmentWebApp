package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpl;
import com.app.dao.VoterDAOImpl;
import com.app.my_pojos.Candidate;
import com.app.my_pojos.Voter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VoterDAOImpl voterDAOObj;
	private CandidateDaoImpl candidateDaoObj;
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try{
			voterDAOObj = new VoterDAOImpl();
			candidateDaoObj = new CandidateDaoImpl();
			System.out.println("Voter DAO created");
			System.out.println("Candidate DAO created");
		}catch(Exception e) {
			throw new ServletException("error in init : " + getClass().getName(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		
		try {
			voterDAOObj.cleanUp();
			candidateDaoObj.cleanUp();

			System.out.println("Voter DAO destroyed");
			System.out.println("Candidate DAO destroyed");
		}
		catch(Exception e) {
			throw new RuntimeException("error in destroy : " + getClass().getName(), e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside do post of login servlet");
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Voter voter = voterDAOObj.login(email, password);
			
			if(voter == null) {
				pw.write("<h5>Invalid Login , Please <a href='login.html'>Retry</a></h5>");
				return;
			}
			
			if(voter.getRole().equals("admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("candidate_dao", candidateDaoObj);
				response.sendRedirect("admin");
				return;
			}
			
			if(voter.getRole().equals("voter")) {
				if(voter.isStatus()) {
					pw.write("<h5>You have already voted , Please <a href='login.html'>Login Again</a></h5>");
					//already voted, redirect to login page
//					response.sendRedirect("status");
				}
				else {
					
					HttpSession session = request.getSession();
					session.setAttribute("voter_details", voter);
					//instead of creating multiple dao instances in init of each servlet, pass it through session
					session.setAttribute("voter_dao", voterDAOObj);
					session.setAttribute("candidate_dao", candidateDaoObj);
					System.out.println("session is " + session.isNew());
					System.out.println("session name is " + session.getId());
					response.sendRedirect("home");
					//still has to vote, show the candidate list
				}
			}
			
		}catch (Exception e) {
			throw new ServletException("error in doPost : " + getClass().getName(), e);
		}
		
	}

}
