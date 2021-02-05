package com.app.dao;

import java.sql.SQLException;

import com.app.my_pojos.Voter;

public interface IVoterDAO {

	Voter login(String email, String password) throws SQLException;
	int updateVotingStatus(int voterId) throws SQLException;
	
}
