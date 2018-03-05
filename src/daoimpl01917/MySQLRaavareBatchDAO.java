package daoimpl01917;

import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareBatchDAO;
import dto01917.RaavareBatchDTO;

public class MySQLRaavareBatchDAO implements RaavareBatchDAO {

	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		Connector.doUpdate(
				"INSERT INTO raavarebatch(raavare_id, raavare_navn, raavare_leverandoer) VALUES " +
				"(" + raavarebatch.getRaavareId() + ", '" + raavarebatch.getRaavareNavn() + "', '" + raavare.getLeverandoer()+ "')"
			);
		
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
