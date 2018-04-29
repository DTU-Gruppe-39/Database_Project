package test01917;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connector01917.Connector;
import daoimpl01917.MySQLOperatoerDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;

public class OperatoerListTest {

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
	public void testOperatorList() {
		
		try {			
			// Calls the getOperatoerList operation and the values of the DTO's in the list is then tested if they are the same as the expected.
			// This test checks both the oprId and the password.
			List<OperatoerDTO> testList = testOp.getOperatoerList();
			OperatoerDTO Opr1DTO = testList.get(0);
			OperatoerDTO Opr2DTO = testList.get(1);
			OperatoerDTO Opr3DTO = testList.get(2);
			OperatoerDTO Opr6DTO = testList.get(3);
			OperatoerDTO Opr7DTO = testList.get(4);
			
			// The test for the operatoer with the oprId 1
			OperatoerDTO testOprDTO1 = new OperatoerDTO(1,"lKje4fa");
			int actual1 = Opr1DTO.getOprId();
			int expected1 = testOprDTO1.getOprId();
			assertEquals(expected1, actual1);
			String actual1p = Opr1DTO.getPassword();
			String expected1p = testOprDTO1.getPassword();
			assertEquals(expected1p, actual1p);
			
			// The test for the operatoer with the oprId 2
			OperatoerDTO testOprDTO2 = new OperatoerDTO(2,"atoJ21v");
			int actual2 = Opr2DTO.getOprId();
			int expected2 = testOprDTO2.getOprId();
			assertEquals(expected2, actual2);
			String actual2p = Opr2DTO.getPassword();
			String expected2p = testOprDTO2.getPassword();
			assertEquals(expected2p, actual2p);
			
			// The test for the operatoer with the oprId 3
			OperatoerDTO testOprDTO3 = new OperatoerDTO(3,"jEfm5aQ");
			int actual3 = Opr3DTO.getOprId();
			int expected3 = testOprDTO3.getOprId();
			assertEquals(expected3, actual3);
			String actual3p = Opr3DTO.getPassword();
			String expected3p = testOprDTO3.getPassword();
			assertEquals(expected3p, actual3p);
			
			// The test for the operatoer with the oprId 6
			OperatoerDTO testOprDTO6 = new OperatoerDTO(6,"UpdateTestPass432");
			int actual6 = Opr6DTO.getOprId();
			int expected6 = testOprDTO6.getOprId();
			assertEquals(expected6, actual6);
			String actual6p = Opr6DTO.getPassword();
			String expected6p = testOprDTO6.getPassword();
			assertEquals(expected6p, actual6p);
			
			// The test for the operatoer with the oprId 7
			OperatoerDTO testOprDTO7 = new OperatoerDTO(7,"testPass324");
			int actual7 = Opr7DTO.getOprId();
			int expected7 = testOprDTO7.getOprId();
			assertEquals(expected7, actual7);
			String actual7p = Opr7DTO.getPassword();
			String expected7p = testOprDTO7.getPassword();
			assertEquals(expected7p, actual7p);
			
		} catch (DALException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
