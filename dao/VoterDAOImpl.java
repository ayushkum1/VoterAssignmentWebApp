package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.app.utils.DBUtils.fetchDBConnection;
import com.app.my_pojos.Voter;

public class VoterDAOImpl implements IVoterDAO{

	private Connection cn;
	private PreparedStatement pst, pst1;
	
	public VoterDAOImpl() throws SQLException, ClassNotFoundException{
	
		String loginStmt = "select * from voters where email=? and password=?";
		String updateVoteStmt = "update voters set status = true where id=?";
		
		cn = fetchDBConnection();
		
		pst = cn.prepareStatement(loginStmt);
		pst1 = cn.prepareStatement(updateVoteStmt);
		
	}

	@Override
	public Voter login(String email, String password) throws SQLException {
		System.out.println("inside login of voter or admin");
		pst.setString(1, email);
		pst.setString(2, password);
		
		try(ResultSet rst = pst.executeQuery()){
			if(rst.next()) {
				return new Voter(rst.getInt(1), rst.getString(2), rst.getString(3),
							rst.getString(4), rst.getBoolean(5), rst.getString(6));
			}
		}
		
		return null;
	}

	@Override
	public int updateVotingStatus(int voterId) throws SQLException {
		
		pst1.setInt(1, voterId);
		
		return pst1.executeUpdate();
	}
	
	public void cleanUp() throws SQLException{
		
		if(pst != null) {
			pst.close();
		}
		if(pst1!=null) {
			pst1.close();
		}
		
		if(cn != null) {
			cn.close();
		}
	}

}
