package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.PersonerDAO;
import dto01917.PersonerDTO;

public class MySQLPersonerDAO implements PersonerDAO {

	@Override
	public PersonerDTO getPersoner(int cpr) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonerDTO> getPersonerList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createPersoner(PersonerDTO per) throws DALException, SQLException {
		
		Connection conn = Connector.getConn();
		PreparedStatement createPerson = null;
		
		String createPer = "INSERT INTO personer(opr_navn, ini, cpr) VALUES " +
						"( ? , ? , ? )";
		
		try {
			createPerson = conn.prepareStatement(createPer);
			
			createPerson.setString(2, per.getOprNavn());
			createPerson.setString(3, per.getIni());
			createPerson.setString(4, per.getCpr());
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (createPerson != null) {
				createPerson.close();
	        }
		}

	}

	@Override
	public void updatePersoner(PersonerDTO per) throws DALException {
		// TODO Auto-generated method stub

	}

}
