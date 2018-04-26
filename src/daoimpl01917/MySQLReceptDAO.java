package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptDAO;
import dto01917.ReceptDTO;

public class MySQLReceptDAO implements ReceptDAO {

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement getRecep = null;
		ResultSet rs = null;
		ReceptDTO ReDTO = null;
		
		String getRcpt = "SELECT * FROM recept WHERE recept_id = ?";
		
		try {
			conn.setAutoCommit(false);
			getRecep = conn.prepareStatement(getRcpt);
			getRecep.setInt(1, receptId);
			rs = getRecep.executeQuery();
			conn.commit();
	    	if (!rs.first()) throw new DALException("Recepten " + receptId + " findes ikke");
			ReDTO = new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn"));
		} catch (SQLException e) {
			//do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getRecep != null) {
				getRecep.close();
			}
			conn.setAutoCommit(true);
		}
		return ReDTO;
	}
		
		
		
		
//		ResultSet rs = Connector.doQuery("SELECT * FROM recept WHERE recept_id = " + receptId);
//	    try {
//	    	if (!rs.first()) throw new DALException("Recepten " + receptId + " findes ikke");
//	    	return new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn"));
//	    }
//	    catch (SQLException e) {throw new DALException(e); }
//	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException, SQLException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		
		Connection conn = Connector.getConn();
		PreparedStatement getRecepList = null;
		ResultSet rs = null;

		String getRcptList = "SELECT * FROM recept";

		try {
			conn.setAutoCommit(false);
			getRecepList = conn.prepareStatement(getRcptList);
			rs = getRecepList.executeQuery();
			conn.commit();
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		} catch (SQLException e) { 
			//throw new DALException(e);
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getRecepList != null) {
				getRecepList.close();
			}
			conn.setAutoCommit(true);
		}
		return list;
	}
		
		
//		ResultSet rs = Connector.doQuery("SELECT * FROM recept");
//		try
//		{
//			while (rs.next()) 
//			{
//				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
//			}
//		}
//		catch (SQLException e) { throw new DALException(e); }
//		return list;
//	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement createRec = null;
		
		String createRcpt = "INSERT INTO recept(recept_id, recept_navn) VALUES " + "(?, ?,)";
		
		try {
			conn.setAutoCommit(false);
			createRec = conn.prepareStatement(createRcpt);

			createRec.setInt(1, recept.getReceptId());
			createRec.setString(2, recept.getReceptNavn());
			createRec.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (createRec != null) {
				createRec.close();
			}
			conn.setAutoCommit(true);
		}
	}
		
		
		
//		Connector.doUpdate(
//				"INSERT INTO recept(recept_id, recept_navn) VALUES " +
//				"(" + recept.getReceptId() + ", '" + recept.getReceptNavn() + "')"
//			);
//	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updateRec = null;

		String updateRcpt = "UPDATE recept SET  recept_id = ?, recept_navn = ? WHERE opr_id = ?";
		
		try {
			conn.setAutoCommit(false);
			updateRec = conn.prepareStatement(updateRcpt);

			updateRec.setInt(1, recept.getReceptId());
			updateRec.setString(2, recept.getReceptNavn());
			updateRec.setInt(3, recept.getReceptId());
			updateRec.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (updateRec != null) {
				updateRec.close();
			}
			conn.setAutoCommit(true);
		}
	}
}
		
//		
//		Connector.doUpdate(
//				"UPDATE recept SET  recept_id = '" + recept.getReceptId() + "', recept_navn =  '" + recept.getReceptNavn() + "' WHERE opr_id = " +
//				recept.getReceptId()
//		);
//	}
//
//}
