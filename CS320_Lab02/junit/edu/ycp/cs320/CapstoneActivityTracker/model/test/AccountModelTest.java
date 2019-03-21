package edu.ycp.cs320.CapstoneActivityTracker.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.ycp.cs320.CapstoneActivityTracker.model.Account;

public class AccountModelTest {

	private Account account;
	private Account testJason;
	private Account testTravis;
	private Account testWill;
	private Account testJohn;
		
	@Before
	public void setUp() throws Exception {
		account = new Account();
		testJason = new Account(1);
		testTravis = new Account(2);	
		testWill = new Account(3);	
		testJohn = new Account(4);	
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
	public void testAccountInt() {
		//check hard code for Jason	
		assertTrue(testJason.getFirstname().equals("Jason"));
		assertTrue(testJason.getLastname().equals("Steinberg"));
		assertTrue(testJason.getEmail().equals("jsteinberg@ycp.edu"));
		assertTrue(testJason.getSchoolID().equals("903123456"));
		assertFalse(testJason.getFaculty());
		
		//check hard code for Travis		
		assertTrue(testTravis.getFirstname().equals("Travis"));
		assertTrue(testTravis.getLastname().equals("Wetzel"));
		assertTrue(testTravis.getEmail().equals("twetzel1@ycp.edu"));
		assertTrue(testTravis.getSchoolID().equals("903110312"));
		assertFalse(testTravis.getFaculty());		
		
		//check hard code for Will	
		assertTrue(testWill.getFirstname().equals("William"));
		assertTrue(testWill.getLastname().equals("Taylor"));
		assertTrue(testWill.getEmail().equals("wtaylor1@ycp.edu"));
		assertTrue(testWill.getSchoolID().equals("903112233"));
		assertFalse(testWill.getFaculty());	
		
		//check hard code for JohnDoe		
		assertTrue(testJohn.getFirstname().equals("John"));
		assertTrue(testJohn.getLastname().equals("Doe"));
		assertTrue(testJohn.getEmail().equals("jdoe@ycp.edu"));
		assertTrue(testJohn.getSchoolID().equals("903123123"));
		assertTrue(testJohn.getFaculty());	
	}
	
	@Test
	public void testValidAccount() {
		assertTrue(testJason.validAccount());
		assertTrue(testTravis.validAccount());
		assertTrue(testWill.validAccount());
		assertTrue(testJohn.validAccount());
		assertFalse(account.validAccount());
	}

	@Test
	public void testCreateAccount() {
		assertFalse(account.verifyCreation());
		
		String firstname = "Robert";
		String lastname = "California";
		String email = "lizardking@ycp.edu";
		String password = "kazamakis";
		String schoolID = "9012334567";
	
		account.createAccount(firstname, lastname, email, password, schoolID);
		assertTrue(account.verifyCreation());
	}
}