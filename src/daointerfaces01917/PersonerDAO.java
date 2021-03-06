package daointerfaces01917;

import java.sql.SQLException;
import java.util.List;

import dto01917.PersonerDTO;


public interface PersonerDAO {
	PersonerDTO getPersoner(int cpr) throws DALException, SQLException;
	List<PersonerDTO> getPersonerList() throws DALException, SQLException;
	void createPersoner(PersonerDTO per) throws DALException, SQLException;
	void updatePersoner(PersonerDTO per) throws DALException, SQLException;
}
