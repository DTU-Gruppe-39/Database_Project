package test01917;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import connector01917.Constant;
import daoimpl01917.MySQLOperatoerDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import junit.framework.Assert;

public class OperatoerGetTests {
	
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
	public void testOprId() {
		
		try {			
			OperatoerDTO testDTO = new OperatoerDTO(2,"atoJ21v");
			OperatoerDTO dbDTO = testOp.getOperatoer(2);
			int actual = dbDTO.getOprId();
			int expected = testDTO.getOprId();
			assertEquals(expected, actual);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testPassword() {
		
		try {			
			OperatoerDTO testDTO = new OperatoerDTO(2,"atoJ21v");
			OperatoerDTO dbDTO = testOp.getOperatoer(2);
			String actual = dbDTO.getPassword();
			String expected = testDTO.getPassword();
			assertEquals(expected, actual);
		} catch (DALException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
