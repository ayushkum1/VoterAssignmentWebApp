package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.my_pojos.Candidate;
import com.app.my_pojos.Voter;

import static com.app.utils.DBUtils.fetchDBConnection;

public class CandidateDaoImpl implements ICandidateDAO{

	private Connection cn;
	private Statement st, st1;
	private PreparedStatement pst;
	private ResultSet rst, rst1;
	
	public CandidateDaoImpl() throws SQLException, ClassNotFoundException{
	
//		String listCandidateStmt = "select * from candidates";
		String incrementVoteStmt = "update candidates set votes = votes + 1 where id=?";
		
		
		cn = fetchDBConnection();
		st = cn.createStatement();
		st1 = cn.createStatement();
//		rst = st.executeQuery(listCandidateStmt); //this caused candidates list to not print as thios statement was already executed and resposne was sent
		pst = cn.prepareStatement(incrementVoteStmt);
		
	}
	
	@Override
	public List<Candidate> listCandidates() throws SQLException {
		System.out.println("inside list generation of candidates");
		List<Candidate> candidates = new ArrayList<>();
		String listCandidateStmt = "select * from candidates";
		rst = st.executeQuery(listCandidateStmt);
		while(rst.next()) {
			candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		
		return candidates;
	}

	@Override
	public String incrementVotes(int candidateId) throws SQLException {
		System.out.println("inside increment votes method");
		pst.setInt(1, candidateId);
		
		if(pst.executeUpdate() == 0) {
			return "Vote Not Added";
		}
		
		return "Voted successfully";
	}

	@Override
	public List<Candidate> listtopTwo() throws SQLException {
		String topTwoCandidateStmt = "select * from candidates order by votes desc limit 2";
		List<Candidate> candidates = new ArrayList<>();
		
		rst = st.executeQuery(topTwoCandidateStmt);
		
		while(rst.next()) {
			candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		
		return candidates;
	}

	public void cleanUp() throws SQLException{
		
		if(pst != null) {
			pst.close();
		}
		
		if(cn != null) {
			cn.close();
		}
	}

}
