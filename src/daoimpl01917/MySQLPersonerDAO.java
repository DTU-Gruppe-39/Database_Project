package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.PersonerDAO;
import dto01917.PersonerDTO;

public class MySQLPersonerDAO implements PersonerDAO {

	@Override
	public PersonerDTO getPersoner(int cpr) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement getPerson = null;
		ResultSet rs = null;
		PersonerDTO perDTO = null;
		
		String getper = "SELECT * FROM personer WHERE opr_id = ?";
		
		try {
			getPerson = conn.prepareStatement(getper);
			getPerson.setInt(1, cpr);
			rs = getPerson.executeQuery();
			if (!rs.first()) throw new DALException("Operatoeren " + cpr + " findes ikke");
			perDTO = new PersonerDTO (rs.getString("cpr"), rs.getString("opr_navn"), rs.getString("ini"));
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (getPerson != null) {
				getPerson.close();
	        }
		}
		return perDTO;
	}

	@Override
	public List<PersonerDTO> getPersonerList() throws DALException, SQLException {
		List<PersonerDTO> list = new ArrayList<PersonerDTO>();

		Connection conn = Connector.getConn();
		PreparedStatement getPersonList = null;
		ResultSet rs = null;
		
		String getperList = "SELECT * FROM personer";
		
		try {
			getPersonList = conn.prepareStatement(getperList);
			rs = getPersonList.executeQuery();
			while (rs.next()) {
					list.add(new PersonerDTO(rs.getString("cpr"), rs.getString("opr_navn"), rs.getString("ini")));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (getPersonList != null) {
				getPersonList.close();
	        }
		}
		return list;
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
			createPerson.executeUpdate();
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
	public void updatePersoner(PersonerDTO per) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updatePerson = null;
		
		String updatePer = "UPDATE operatoer SET opr_navn = ?, ini = ? WHERE cpr = ?";
		
		try {
			updatePerson = conn.prepareStatement(updatePer);
			
			updatePerson.setString(1, per.getOprNavn());
			updatePerson.setString(2, per.getIni());
			updatePerson.setString(3, per.getCpr());
			updatePerson.executeUpdate();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
		} finally {
			if (updatePerson != null) {
				updatePerson.close();
	        }
		}
	}

}
