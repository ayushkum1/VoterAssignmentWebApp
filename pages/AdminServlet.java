package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpl;
import com.app.my_pojos.Candidate;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside do get of admin servlet");
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		
		CandidateDaoImpl caDaoImpl = (CandidateDaoImpl)session.getAttribute("candidate_dao");
		
		try(PrintWriter pw = response.getWriter()){
			pw.write("<h1>Welcome Rama(Admin)</h1><br>");
			pw.write("<h2> The Top Two Candidates with maximum votes are : </h2> <br>");
			
			List<Candidate> candidates = caDaoImpl.listtopTwo();
			
			for(Candidate c : candidates) {
				System.out.println(c);
				pw.write("<h3>" + c + " Votes = " + c.getVotes() + "</h3><br>");
			}
			
			pw.write("<br><h3><a href = 'login.html'>Go Back</h3>");
			
		}catch (Exception e) {
			System.out.println("Exception occured in " + e.getClass().getName() + " and exception is " + e.getMessage());
		} 
		
		
	}

}
