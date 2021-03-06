package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareDAO;
import dto01917.RaavareDTO;

public class MySQLRaavareDAO implements RaavareDAO{

	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement getraavare = null;
		ResultSet rs = null;
		RaavareDTO raaDTO = null;
		
		String getraa = "SELECT * FROM raavare WHERE raavare_id = ?";
		
		try {
			getraavare = conn.prepareStatement(getraa);
			getraavare.setInt(1, raavareId);
			rs = getraavare.executeQuery();
			if (!rs.first()) throw new DALException("Raavaren med id:  " + raavareId + " findes ikke");
			raaDTO = new RaavareDTO (rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer"));
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (getraavare != null) {
				getraavare.close();
	        }
		}
		return raaDTO;
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException, SQLException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();

		Connection conn = Connector.getConn();
		PreparedStatement getRaavareList = null;
		ResultSet rs = null;
		
		String getRaaList = "SELECT * FROM raavare";
		
		try {
			getRaavareList = conn.prepareStatement(getRaaList);
			rs = getRaavareList.executeQuery();
			while (rs.next()) {
					list.add(new RaavareDTO(rs.getInt("raavare_id"),rs.getString("raavare_leverandoer"),rs.getString("raavare_navn")));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (getRaavareList != null) {
				getRaavareList.close();
	        }
		}
		return list;
	}

	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException, SQLException {	
		Connection conn = Connector.getConn();
		PreparedStatement createRaavare = null;
		
		String createRaa = "INSERT INTO raavare(raavare_id, raavare_navn, raavare_leverandoer) VALUES ( ? , ? , ? )";

		try {
			createRaavare = conn.prepareStatement(createRaa);
			
			createRaavare.setInt(1, raavare.getRaavareId());
			createRaavare.setString(2, raavare.getRaavareNavn());
			createRaavare.setString(3, raavare.getLeverandoer());
			createRaavare.executeUpdate();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (createRaavare != null) {
				createRaavare.close();
	        }
		}
	}
		

	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updateRaavare = null;
		
		String updateRaa = "UPDATE raavare SET raavare_navn = ? , raavare_leverandoer = ? WHERE raavare_id = ?";
		
		try {
			updateRaavare = conn.prepareStatement(updateRaa);
			updateRaavare.setString(1, raavare.getRaavareNavn());
			updateRaavare.setString(2, raavare.getLeverandoer());
			updateRaavare.setInt(3, raavare.getRaavareId());
			updateRaavare.executeUpdate();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (updateRaavare != null) {
				updateRaavare.close();
	        }
		}
	}
}
