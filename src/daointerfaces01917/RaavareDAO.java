package daointerfaces01917;

import java.sql.SQLException;
import java.util.List;

import dto01917.RaavareDTO;

public interface RaavareDAO {
	RaavareDTO getRaavare(int raavareId) throws DALException, SQLException;
	List<RaavareDTO> getRaavareList() throws DALException, SQLException;
	void createRaavare(RaavareDTO raavare) throws DALException, SQLException;
	void updateRaavare(RaavareDTO raavare) throws DALException, SQLException;
}
