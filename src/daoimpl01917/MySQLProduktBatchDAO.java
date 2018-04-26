package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import dto01917.ProduktBatchDTO;
import daointerfaces01917.ProduktBatchDAO;

public class MySQLProduktBatchDAO implements ProduktBatchDAO {
	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement getProBatch = null;
		ResultSet rs = null;
		ProduktBatchDTO PbDTO = null;
		
		String getProBa = "SELECT * FROM produktbatch WHERE pb_id = ?";
		
		try {
			conn.setAutoCommit(false);
			getProBatch = conn.prepareStatement(getProBa);
			getProBatch.setInt(1, pbId);
			rs = getProBatch.executeQuery();
			if (!rs.first()) throw new DALException("Produktbatchet " + pbId + " findes ikke");
			PbDTO = new ProduktBatchDTO (rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id"));
			conn.commit();
		} catch (SQLException e) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getProBatch != null) {
				getProBatch.close();
			}
			conn.setAutoCommit(true);
		}
		return PbDTO;
	}
	

	
	@Override
	public void createProduktBatch(ProduktBatchDTO pb) throws DALException, SQLException {		
		Connection conn = Connector.getConn();
		PreparedStatement createProBatch = null;

		String createProBa = "INSERT INTO produktbatch(pb_id, status, recept_id) VALUES " + 
				"(?, ?, ?)";

		try {
			conn.setAutoCommit(false);
			createProBatch = conn.prepareStatement(createProBa);

			createProBatch.setInt(1, pb.getPbId());
			createProBatch.setInt(2, pb.getStatus());
			createProBatch.setInt(3, pb.getReceptId());
			createProBatch.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (createProBatch != null) {
				createProBatch.close();
			}
			conn.setAutoCommit(true);
		}
	}
	@Override
	public void updateProduktBatch(ProduktBatchDTO pb) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updateProBatch = null;

		String updateProBa = "UPDATE produktbatch SET pb_id = ? , status = ? , recept_id = ? WHERE pr_id = ?";

		try {
			conn.setAutoCommit(false);
			updateProBatch = conn.prepareStatement(updateProBa);

			updateProBatch.setInt(1, pb.getPbId());
			updateProBatch.setInt(2, pb.getStatus());
			updateProBatch.setInt(3, pb.getReceptId());
			updateProBatch.setInt(4, pb.getPbId());
			updateProBatch.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (updateProBatch != null) {
				updateProBatch.close();
			}
			conn.setAutoCommit(true);
		}
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException, SQLException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();

		Connection conn = Connector.getConn();
		PreparedStatement getProdBatchList = null;
		ResultSet rs = null;

		String getProBaList = "SELECT * FROM produktbatch";

		try {
			conn.setAutoCommit(false);
			getProdBatchList = conn.prepareStatement(getProBaList);
			rs = getProdBatchList.executeQuery();
			conn.commit();
			while (rs.next()) 
			{
				list.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id")));
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
			if (getProdBatchList != null) {
				getProdBatchList.close();
			}
			conn.setAutoCommit(true);
		}
		return list;
	}
}
