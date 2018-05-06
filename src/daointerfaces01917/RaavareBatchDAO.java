package daointerfaces01917;

import java.sql.SQLException;
import java.util.List;

import dto01917.RaavareBatchDTO;

public interface RaavareBatchDAO {
	RaavareBatchDTO getRaavareBatch(int rbId) throws DALException, SQLException;
	List<RaavareBatchDTO> getRaavareBatchList() throws DALException, SQLException;
	List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException, SQLException;
	void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException, SQLException;
	void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException, SQLException;
}

