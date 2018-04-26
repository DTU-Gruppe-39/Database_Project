package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RollerDAO;
import dto01917.RollerDTO;
import dto01917.rolleEnum;

public class MySQLRollerDAO implements RollerDAO {

	@Override
	public RollerDTO getRoller(int opr_id, int cpr) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement getRoller = null;
		ResultSet rs = null;
		RollerDTO rolleDTO = null;
		
		String getRolle = "SELECT * FROM roller WHERE opr_id = ?";
		
		try {
			conn.setAutoCommit(false);
			getRoller = conn.prepareStatement(getRolle);
			getRoller.setInt(1, opr_id);
			rs = getRoller.executeQuery();
			conn.commit();
			if (!rs.first()) throw new DALException("Cpr nummeret " + cpr + " findes ikke");
			rolleDTO = new RollerDTO (rs.getInt("opr_id"), rs.getString("cpr"), rolleEnum.valueOf(rs.getString("rolle")));
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getRoller != null) {
				getRoller.close();
	        }
			conn.setAutoCommit(true);
		}
		return rolleDTO;
	}

	@Override
	public List<RollerDTO> getAllRoller(String cpr) throws DALException, SQLException {
		List<RollerDTO> list = new ArrayList<RollerDTO>();
		Connection conn = Connector.getConn();
		PreparedStatement getRollerList = null;
		ResultSet rs = null;
		
		String getRolleList = "SELECT * FROM roller WHERE cpr = ?";
		
		try {
			conn.setAutoCommit(false);
			getRollerList = conn.prepareStatement(getRolleList);
			getRollerList.setString(1, cpr);
			rs = getRollerList.executeQuery();
			conn.commit();
			while (rs.next()) {
					list.add(new RollerDTO(rs.getInt("opr_id"), rs.getString("cpr"), rolleEnum.valueOf(rs.getString("rolle"))));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getRollerList != null) {
				getRollerList.close();
	        }
			conn.setAutoCommit(true);
		}
		return list;
	}

	@Override
	public List<RollerDTO> getRollerList() throws DALException, SQLException {
		List<RollerDTO> list = new ArrayList<RollerDTO>();

		Connection conn = Connector.getConn();
		PreparedStatement getRollerList = null;
		ResultSet rs = null;
		
		String getRolleList = "SELECT * FROM roller";
		
		try {
			conn.setAutoCommit(false);
			getRollerList = conn.prepareStatement(getRolleList);
			rs = getRollerList.executeQuery();
			conn.commit();
			while (rs.next()) {
					list.add(new RollerDTO(rs.getInt("opr_id"), rs.getString("cpr"), rolleEnum.valueOf(rs.getString("rolle"))));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getRollerList != null) {
				getRollerList.close();
	        }
			conn.setAutoCommit(true);
		}
		return list;
	}

	@Override
	public void createRoller(RollerDTO rolle) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement createRoller = null;
		
		String createRolle = "INSERT INTO rolle(opr_id, cpr, rolle) VALUES " +
						"( ? , ? , ? )";
		
		try {
			conn.setAutoCommit(false);
			createRoller = conn.prepareStatement(createRolle);
			createRoller.setInt(1, rolle.getOprId());
			createRoller.setString(2, rolle.getCpr());
			createRoller.setString(3, rolle.getRolle().toString());
			createRoller.executeUpdate();
			conn.commit();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (createRoller != null) {
				createRoller.close();
	        }
			conn.setAutoCommit(true);
		}
		
	}

	@Override
	public void updateRoller(RollerDTO rolle) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updateRoller = null;
		
		String updateRolle = "UPDATE rolle SET rolle = ? WHERE opr_id = ?";
		
		try {
			conn.setAutoCommit(false);
			updateRoller = conn.prepareStatement(updateRolle);
			
			updateRoller.setString(1, rolle.getRolle().toString());
			updateRoller.setInt(2, rolle.getOprId());
			updateRoller.executeUpdate();
			conn.commit();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (updateRoller != null) {
				updateRoller.close();
	        }
			conn.setAutoCommit(true);
		}
		
	}



}
