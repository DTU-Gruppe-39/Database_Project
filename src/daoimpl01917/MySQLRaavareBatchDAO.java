package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareBatchDAO;
import dto01917.RaavareBatchDTO;

public class MySQLRaavareBatchDAO implements RaavareBatchDAO {

	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement getraavareBatch = null;
		ResultSet rs = null;
		RaavareBatchDTO raaBaDTO = null;
		
		String getraaBa = "SELECT * FROM raavarebatch WHERE rbId_id = ?";
		
		try {
			getraavareBatch = conn.prepareStatement(getraaBa);
			getraavareBatch.setInt(1, rbId);
			rs = getraavareBatch.executeQuery();
			if (!rs.first()) throw new DALException("RaavareBatchen med id:  " + rbId + " findes ikke");
			raaBaDTO = new RaavareBatchDTO (rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde"));
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (getraavareBatch != null) {
				getraavareBatch.close();
	        }
		}
		return raaBaDTO;
	}


	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException, SQLException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		Connection conn = Connector.getConn();
		PreparedStatement getRaavareBatchList = null;
		ResultSet rs = null;
		
		String getRaaBaList = "SELECT * FROM raavarebatch";
		
		try {
			getRaavareBatchList = conn.prepareStatement(getRaaBaList);
			rs = getRaavareBatchList.executeQuery();
			while (rs.next()) {
					list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde")));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (getRaavareBatchList != null) {
				getRaavareBatchList.close();
	        }
		}
		return list;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException, SQLException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		Connection conn = Connector.getConn();
		PreparedStatement getRaavareBatchList = null;
		ResultSet rs = null;
		
		String getRaaBaList = "SELECT * FROM raavarebatch WHERE raavareId = ?";
		
		try {
			getRaavareBatchList = conn.prepareStatement(getRaaBaList);
			getRaavareBatchList.setInt(1, raavareId);
			rs = getRaavareBatchList.executeQuery();
			while (rs.next()) {
					list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde")));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (getRaavareBatchList != null) {
				getRaavareBatchList.close();
	        }
		}
		return list;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement createRaavareBatch = null;
		
		String createRaaBa = "INSERT INTO raavarebatch(rb_id, raavare_id, maengde) VALUES ( ? , ? , ? )";

		try {
			createRaavareBatch = conn.prepareStatement(createRaaBa);
			
			createRaavareBatch.setInt(1, raavarebatch.getRbId());
			createRaavareBatch.setInt(2, raavarebatch.getRaavareId());
			createRaavareBatch.setDouble(3, raavarebatch.getMaengde());
			createRaavareBatch.executeUpdate();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (createRaavareBatch != null) {
				createRaavareBatch.close();
	        }
		}
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updateRaavareBatch = null;
		
		String updateRaaBa = "UPDATE raavarebatch SET maengde = ? WHERE rb_id = ?";
		
		try {
			updateRaavareBatch = conn.prepareStatement(updateRaaBa);
			updateRaavareBatch.setDouble(1, raavarebatch.getMaengde());
			updateRaavareBatch.setInt(2, raavarebatch.getRbId());
			updateRaavareBatch.setInt(3, raavarebatch.getRaavareId());
			updateRaavareBatch.executeUpdate();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (updateRaavareBatch != null) {
				updateRaavareBatch.close();
	        }
		}
	}
}