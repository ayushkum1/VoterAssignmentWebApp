package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("inside do get of Home servlet");
		response.setContentType("text/html");

		HttpSession session = request.getSession();
		
		Voter voter = (Voter)session.getAttribute("voter_details");
//		VoterDAOImpl voterDAOObj = (VoterDAOImpl)session.getAttribute("voter_dao");
		CandidateDaoImpl candidateDaoObject = (CandidateDaoImpl)session.getAttribute("candidate_dao");
		
		try (PrintWriter pw = response.getWriter()) {

			List<Candidate> candidates = candidateDaoObject.listCandidates();
			candidates.forEach(System.out::println);
			pw.print("<form action='status'>");

			pw.write("<h2>Welcome "+ voter.getName() +"</h2>");
			
			for (Candidate c : candidates) {
				pw.print("<h3><input type='radio' name='cid' value=" + c.getId() + ">"
						/* + c.getCandidateName() + "(" + c.getParty() + ")> " */ + c + "<br></h3>");
			}
			
//			voterDAOObj.updateVotingStatus();
			
			pw.print("<button type='submit' > vote </button>");
			pw.print("</form>");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
