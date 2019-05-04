package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.ycp.cs320.CapstoneActivityTracker.model.Account;
import edu.ycp.cs320.CapstoneActivityTracker.model.AdminAccount;
import edu.ycp.cs320.CapstoneActivityTracker.model.StudentAccount;

public class AccountModelTest {

	private Account account;
	private Account testJason;
	private Account testTravis;
	private Account testWill;
	private Account testJohn;
		
	@Before
	public void setUp() throws Exception {
		account = new Account();
		testJohn = new AdminAccount("John", "Doe", "jdoe@ycp.edu", "password" , "900000000");
		testJason = new StudentAccount("Jason", "Steinberg", "jsteinberg@ycp.edu", "password", "900000001");
		testTravis = new StudentAccount("Travis", "Wetzel", "twetzel1@ycp.edu", "password", "900000002");
		testWill = new StudentAccount("William", "Taylor", "wtaylor1@ycp.edu", "password", "900000003");
	}

	@Test
	public void testSetFirstname() {
		String name = "Jim";
		account.setFirstname(name);
		assertTrue(account.getFirstname().equals("Jim"));
		account.setFirstname("Dwight");
		assertFalse(account.getFirstname().equals(name));
	}

	@Test
	public void testSetLastname() {
		String name = "Halpert";
		account.setLastname(name);
		assertTrue(account.getLastname().equals("Halpert"));
		account.setLastname("Schrute");
		assertFalse(account.getLastname().equals(name));
	}

	@Test
	public void testSetEmail() {
		String email = "mscott@ycp.edu";
		account.setEmail(email);
		assertTrue(account.getEmail().equals("mscott@ycp.edu"));
		account.setEmail("pbeesly@ycp.edu");
		assertFalse(account.getEmail().equals(email));
	}

	@Test
	public void testSetSchoolID() {
		String id = "901234567";
		account.setSchoolID(id);
		assertTrue(account.getSchoolID().equals("901234567"));
		account.setSchoolID("9076543210");
		assertFalse(account.getSchoolID().equals(id));
	}

	@Test
	public void testSetPassword() {
		String password = "beets";
		account.setPassword(password);
		assertTrue(account.getPassword().equals("beets"));
		account.setFirstname("bears");
		assertFalse(account.getFirstname().equals(password));
	}
	
	@Test
	public void testSetAccountID() {
		int id = 1;
		account.setAccountID(id);
		assertEquals(1, account.getAccountID());
		account.setAccountID(2);
		assertNotEquals(id, account.getAccountID());
	}
	
	
	@Test
	public void testAccount() {
		//check hard code for Jason	
		assertTrue(testJason.getFirstname().equals("Jason"));
		assertTrue(testJason.getLastname().equals("Steinberg"));
		assertTrue(testJason.getEmail().equals("jsteinberg@ycp.edu"));
		assertTrue(testJason.getSchoolID().equals("900000001"));
		
		//check hard code for Travis		
		assertTrue(testTravis.getFirstname().equals("Travis"));
		assertTrue(testTravis.getLastname().equals("Wetzel"));
		assertTrue(testTravis.getEmail().equals("twetzel1@ycp.edu"));
		assertTrue(testTravis.getSchoolID().equals("900000002"));
		
		//check hard code for Will	
		assertTrue(testWill.getFirstname().equals("William"));
		assertTrue(testWill.getLastname().equals("Taylor"));
		assertTrue(testWill.getEmail().equals("wtaylor1@ycp.edu"));
		assertTrue(testWill.getSchoolID().equals("900000003"));
		
		//check hard code for JohnDoe		
		assertTrue(testJohn.getFirstname().equals("John"));
		assertTrue(testJohn.getLastname().equals("Doe"));
		assertTrue(testJohn.getEmail().equals("jdoe@ycp.edu"));
		assertTrue(testJohn.getSchoolID().equals("900000000"));
	}

	@Test
	public void testSetFaculty() {
		account.setFaculty(true);
		assertTrue(account.getFaculty());
		account.setFaculty(false);
		assertFalse(account.getFaculty());
	}
	
	
}