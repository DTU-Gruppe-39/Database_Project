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
	 * Done
	 * @throws SQLException 
	 */
	public OperatoerDTO getOperatoer(int oprId) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement getOperat = null;
		ResultSet rs = null;
		OperatoerDTO oprDTO = null;
		
		String getOpr = "SELECT * FROM operatoer WHERE opr_id = ?";
		
		try {
			conn.setAutoCommit(false);
			getOperat = conn.prepareStatement(getOpr);
			getOperat.setInt(1, oprId);
			rs = getOperat.executeQuery();
			conn.commit();
			if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
			oprDTO = new OperatoerDTO (rs.getInt("opr_id"), rs.getString("password"));
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getOperat != null) {
				getOperat.close();
	        }
			conn.setAutoCommit(true);
		}
		return oprDTO;
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
			conn.setAutoCommit(false);
			createOperat = conn.prepareStatement(createOpr);
			
			createOperat.setInt(1, opr.getOprId());
			createOperat.setString(2, opr.getPassword());
			createOperat.executeUpdate();
			conn.commit();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (createOperat != null) {
				createOperat.close();
	        }
			conn.setAutoCommit(true);
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
			conn.setAutoCommit(false);
			updateOperat = conn.prepareStatement(updateOpr);
			
			updateOperat.setString(1, opr.getPassword());
			updateOperat.setInt(2, opr.getOprId());
			updateOperat.executeUpdate();
			conn.commit();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (updateOperat != null) {
				updateOperat.close();
	        }
			conn.setAutoCommit(true);
		}
	}
	
	/**
	 * Done
	 * @throws SQLException 
	 */
	public List<OperatoerDTO> getOperatoerList() throws DALException, SQLException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();

		Connection conn = Connector.getConn();
		PreparedStatement getOperatList = null;
		ResultSet rs = null;
		
		String getOprList = "SELECT * FROM operatoer";
		
		try {
			conn.setAutoCommit(false);
			getOperatList = conn.prepareStatement(getOprList);
			rs = getOperatList.executeQuery();
			conn.commit();
			while (rs.next()) {
					list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("password")));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getOperatList != null) {
				getOperatList.close();
	        }
			conn.setAutoCommit(true);
		}
		return list;
	}
		
		
}
	
