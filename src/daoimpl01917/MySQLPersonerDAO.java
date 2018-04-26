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
			conn.setAutoCommit(false);
			getPerson = conn.prepareStatement(getper);
			getPerson.setInt(1, cpr);
			rs = getPerson.executeQuery();
			if (!rs.first()) throw new DALException("Personen " + cpr + " findes ikke");
			perDTO = new PersonerDTO (rs.getString("cpr"), rs.getString("opr_navn"), rs.getString("ini"));
			conn.commit();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getPerson != null) {
				getPerson.close();
	        }
			conn.setAutoCommit(true);
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
			conn.setAutoCommit(false);
			getPersonList = conn.prepareStatement(getperList);
			rs = getPersonList.executeQuery();
			conn.commit();
			while (rs.next()) {
					list.add(new PersonerDTO(rs.getString("cpr"), rs.getString("opr_navn"), rs.getString("ini")));
				}
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (getPersonList != null) {
				getPersonList.close();
	        }
			conn.setAutoCommit(true);
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
			conn.setAutoCommit(false);
			createPerson = conn.prepareStatement(createPer);
			
			createPerson.setString(2, per.getOprNavn());
			createPerson.setString(3, per.getIni());
			createPerson.setString(4, per.getCpr());
			createPerson.executeUpdate();
			conn.commit();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (createPerson != null) {
				createPerson.close();
	        }
			conn.setAutoCommit(true);
		}

	}

	@Override
	public void updatePersoner(PersonerDTO per) throws DALException, SQLException {
		Connection conn = Connector.getConn();
		PreparedStatement updatePerson = null;
		
		String updatePer = "UPDATE personer SET opr_navn = ?, ini = ? WHERE cpr = ?";
		
		try {
			conn.setAutoCommit(false);
			updatePerson = conn.prepareStatement(updatePer);
			
			updatePerson.setString(1, per.getOprNavn());
			updatePerson.setString(2, per.getIni());
			updatePerson.setString(3, per.getCpr());
			updatePerson.executeUpdate();
			conn.commit();
		} catch (SQLException e ) {
			//Do error handling
			//TODO
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("Transation was rolled back");
			conn.rollback();
		} finally {
			if (updatePerson != null) {
				updatePerson.close();
	        }
			conn.setAutoCommit(true);
		}
	}

}
