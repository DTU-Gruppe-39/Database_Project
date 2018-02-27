package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;
import daointerfaces01917.ProduktBatchDAO;

public class MySQLProduktBatchDAO implements ProduktBatchDAO {
	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch WHERE pb_id = " + pbId);
	    try {
	    	if (!rs.first()) throw new DALException("Produktbatchet " + pbId + " findes ikke");
	    	return new ProduktBatchDTO (rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	@Override
	public void createProduktBatch(ProduktBatchDTO pb) throws DALException {		
			Connector.doUpdate(
				"INSERT INTO produktbatch(pb_id, status, recept_id) VALUES " +
				"(" + pb.getPbId() + ", '" + pb.getStatus() + "', '" + pb.getReceptId() + "')"
			);
	}
	@Override
	public void updateProduktBatch(ProduktBatchDTO pb) throws DALException {
		Connector.doUpdate(
				"UPDATE produktbatch SET  pb_id = '" + pb.getPbId() + "', status =  '" + pb.getStatus() + 
				"', recept_id = '" + pb.getReceptId() + "' WHERE pb_id = " +
				pb.getPbId()
		);
	}
	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch");
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
}
