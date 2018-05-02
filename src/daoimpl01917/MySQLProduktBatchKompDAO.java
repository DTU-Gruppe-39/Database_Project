package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchKompDAO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement getProBatchKomp = null;
		ResultSet rs = null;
		ProduktBatchKompDTO PbkDTO = null;

		String getProBaKo = "SELECT * FROM produktbatchkomponent WHERE pb_id = ? AND rb_id = ?";

		try {
			getProBatchKomp = conn.prepareStatement(getProBaKo);
			getProBatchKomp.setInt(1, pbId);
			getProBatchKomp.setInt(2, rbId);
			rs = getProBatchKomp.executeQuery();
			if (!rs.first()) throw new DALException("Produktbatchkomponent ID: " + pbId + "eller Raavarebatch ID: " + rbId + " findes ikke");
			PbkDTO = new ProduktBatchKompDTO (rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"));
		} catch (SQLException e) {
			//do error handling
			//TODO
		} finally {
			if (getProBatchKomp != null) {
				getProBatchKomp.close();
			}
		}
		return PbkDTO;
	}


	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException, SQLException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();

		Connection conn = Connector.getConn();
		PreparedStatement getProdBatchKompList = null;
		ResultSet rs = null;

		String getProBaKoList = "SELECT * FROM produktbatchkomponent WHERE pb_id = ?";

		try {
			getProdBatchKompList = conn.prepareStatement(getProBaKoList);

			getProdBatchKompList.setInt(1, pbId);
			rs = getProdBatchKompList.executeQuery();
			while (rs.next())
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
		} catch (SQLException e) {
			//Do error handling
			//TODO
		} finally {
			if (getProdBatchKompList != null) {
				getProdBatchKompList.close();
			}
		}
		return list;
	}


	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException, SQLException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		
		Connection conn = Connector.getConn();
		PreparedStatement getProdBatchListKomp = null;
		ResultSet rs = null;

		String getProBaListKo = "SELECT * FROM produktbatchkomponent";

		try {
			getProdBatchListKomp = conn.prepareStatement(getProBaListKo);
			rs = getProdBatchListKomp.executeQuery();
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
		} catch (SQLException e) { 
			throw new DALException(e);
			//Do error handling
			//TODO
		} finally {
			if (getProdBatchListKomp != null) {
				getProdBatchListKomp.close();
			}
		}
		return list;
	}
		
		
		
//		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent");
//		try
//		{
//			while (rs.next()) 
//			{
//				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
//			}
//		}
//		catch (SQLException e) { throw new DALException(e); }
//		return list;
//	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement createProBatchKomp = null;
		
		String createProBaKo = "INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES " + "(?, ?, ?, ?, ?)";
		
		try {
			createProBatchKomp = conn.prepareStatement(createProBaKo);

			createProBatchKomp.setInt(1, produktbatchkomponent.getPbId());
			createProBatchKomp.setInt(2, produktbatchkomponent.getRbId());
			createProBatchKomp.setDouble(3, produktbatchkomponent.getTara());
			createProBatchKomp.setDouble(4, produktbatchkomponent.getNetto());
			createProBatchKomp.setInt(5, produktbatchkomponent.getOprId());
			createProBatchKomp.executeUpdate();
		} catch (SQLException e) {
			//Do error handling
			//TODO
		} finally {
			if (createProBatchKomp != null) {
				createProBatchKomp.close();
			}
		}
	}
		
//		Connector.doUpdate(
//				"INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES " +
//						"(" + produktbatchkomponent.getPbId() + ", " + produktbatchkomponent.getRbId() + ", " + produktbatchkomponent.getTara() + ", " + 
//						produktbatchkomponent.getNetto() + ", " + produktbatchkomponent.getOprId() + ")"
//				);
//	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updateProBatchKomp = null;

		String updateProBaKo = "UPDATE produktbatchkomponent SET  pb_id = ?, rb_id = ?, tara = ?, netto = ?, opr_id = ? WHERE pb_id = ? AND rb_id = ?";
		
		try {
			updateProBatchKomp = conn.prepareStatement(updateProBaKo);

			updateProBatchKomp.setInt(1, produktbatchkomponent.getPbId());
			updateProBatchKomp.setInt(2, produktbatchkomponent.getRbId());
			updateProBatchKomp.setDouble(3, produktbatchkomponent.getTara());
			updateProBatchKomp.setDouble(4, produktbatchkomponent.getNetto());
			updateProBatchKomp.setInt(5, produktbatchkomponent.getOprId());
			updateProBatchKomp.setInt(6, produktbatchkomponent.getPbId());
			updateProBatchKomp.setInt(7, produktbatchkomponent.getRbId());
			updateProBatchKomp.executeUpdate();
		} catch (SQLException e) {
			//Do error handling
			//TODO
		} finally {
			if (updateProBatchKomp != null) {
				updateProBatchKomp.close();
			}
		}
	}
}
//		Connector.doUpdate(
//				"UPDATE produktbatchkomponent SET  pb_id = " + produktbatchkomponent.getPbId() + ", rb_id =  " + produktbatchkomponent.getRbId() + 
//				", tara = " + produktbatchkomponent.getTara() + ", netto = " + produktbatchkomponent.getNetto() + ", opr_id = " + produktbatchkomponent.getOprId() + 
//				"WHERE pb_id = " + produktbatchkomponent.getPbId() +" AND rb_id = " + produktbatchkomponent.getRbId());
//	}
//
//}
