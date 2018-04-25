package daoimpl01917;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;

public class MySQLOperatoerDAO implements OperatoerDAO {
	/**
	 * Missing
	 */
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer WHERE opr_id = " + oprId);
	    try {
	    	if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	/**
	 * Done
	 */
	public void createOperatoer(OperatoerDTO opr) throws DALException, SQLException {		
		Connection conn = Connector.getConn();
		PreparedStatement createOperat = null;
		
		String createOpr = "INSERT INTO operatoer(opr_id, password) VALUES " +
						"( ? , ? )";

		
		try {
			createOperat = conn.prepareStatement(createOpr);
			
			createOperat.setInt(1, opr.getOprId());
			createOperat.setString(2, opr.getPassword());
			createOperat.executeUpdate();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (createOperat != null) {
				createOperat.close();
	        }
		}
	}
	
	/**
	 * Done
	 * @throws SQLException 
	 */
	public void updateOperatoer(OperatoerDTO opr) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updateOperat = null;
		
		String updateOpr = "UPDATE operatoer SET password = ? WHERE opr_id = ?";
		
		try {
			updateOperat = conn.prepareStatement(updateOpr);
			
			updateOperat.setString(1, opr.getPassword());
			updateOperat.setInt(2, opr.getOprId());
			updateOperat.executeUpdate();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (updateOperat != null) {
				updateOperat.close();
	        }
		}
	}
	
	/**
	 * Missing
	 * @throws SQLException 
	 */
	public List<OperatoerDTO> getOperatoerList() throws DALException, SQLException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();

		Connection conn = Connector.getConn();
		PreparedStatement getOperatList = null;
		ResultSet rs = null;
		
		String getOprList = "SELECT * FROM operatoer";
		
		try {
			getOperatList = conn.prepareStatement(getOprList);
			rs = getOperatList.executeQuery();
			while (rs.next()) {
					list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("password")));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (getOperatList != null) {
				getOperatList.close();
	        }
		}
		return list;
		
		
		
		
//		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer");
//		try
//		{
//			while (rs.next()) 
//			{
//				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("password")));
//			}
//		}
//		catch (SQLException e) { throw new DALException(e); }
	}
		
		
}
	
