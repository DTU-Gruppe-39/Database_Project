package test01917;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daoimpl01917.MySQLOperatoerDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;

public class OperatoerUpdateTest {

	MySQLOperatoerDAO testOp = new MySQLOperatoerDAO();
	
	@Before
	public void setUp() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
	

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdateOperator() {
		
		try {			
			OperatoerDTO testDTO = new OperatoerDTO(6,"UpdateTestPass432");
			testOp.updateOperatoer(testDTO);
			OperatoerDTO dbDTO = testOp.getOperatoer(6);
			int actual = dbDTO.getOprId();
			int expected = testDTO.getOprId();
			assertEquals(expected, actual);
			String actual2 = dbDTO.getPassword();
			String expected2 = testDTO.getPassword();
			assertEquals(expected2, actual2);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}