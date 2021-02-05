package com.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.app.my_pojos.Candidate;

public interface ICandidateDAO {

	List<Candidate> listCandidates() throws SQLException;
	String incrementVotes(int candidateId) throws SQLException;
	List<Candidate> listtopTwo() throws SQLException;
}
