package daointerfaces01917;

import java.sql.SQLException;
import java.util.List;

import dto01917.ReceptDTO;

public interface ReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException, SQLException;
	List<ReceptDTO> getReceptList() throws DALException, SQLException;
	void createRecept(ReceptDTO recept) throws DALException, SQLException;
	void updateRecept(ReceptDTO recept) throws DALException, SQLException;
}
