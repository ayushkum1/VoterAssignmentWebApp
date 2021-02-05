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
import com.app.my_pojos.Voter;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/status")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside do get of status servlet");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		
		VoterDAOImpl voDaoImpl = (VoterDAOImpl)session.getAttribute("voter_dao");
		CandidateDaoImpl cDaoImpl = (CandidateDaoImpl)session.getAttribute("candidate_dao");
		Voter voter = (Voter)session.getAttribute("voter_details");
		
		try(PrintWriter pw = response.getWriter()){
			
			if(voter.isStatus()) {
				pw.write("<h4>you have already voted! <a href='login.html'>Go Back</a> </h4>");
			}
			else {
				try {
					System.out.println("in try of current exception");
					int result = voDaoImpl.updateVotingStatus(voter.getId());
					System.out.println("result is " + result);
					if(result > 0) {
						System.out.println("inside status else part");
						System.out.println(cDaoImpl.incrementVotes(Integer.parseInt(request.getParameter("cid"))));
						pw.write("<h4>you have successfully voted!!! <a href='login.html'>Go Back</a> </h4>");
					}
					else {
						pw.write("<h4>Couldnt update your vote, Pls try again <a href='login.html'>Go Back</a> </h4>");
					}
					session.invalidate();
				}catch (Exception e) {
					System.out.println("Exception occured in " + getClass().getName() + " and exception is " + e);
				}
			}
		}
	}

}
