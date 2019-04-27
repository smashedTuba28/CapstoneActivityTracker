package edu.ycp.cs320.CpastoneActivityTracker.controller.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CapstonActivtyTracker.db.hashSHA256;
import edu.ycp.cs320.CapstoneActivityTracker.controller.SignInController;
import edu.ycp.cs320.CapstoneActivityTracker.model.Account;

public class SignInControllerTest {
	private Account account;
	private SignInController controller;
	private Account model = null;
	
	

	@Before
	public void setUp() throws Exception {
		controller = new SignInController();
		account = new Account("Jason", "Steinberg", "jsteinberg@ycp.edu", "password", "900000000");
		controller.setModel(account);
	}

	@Test
	public void testGetModel() {
		assertTrue(model == null);
		model = controller.getModel();
		assertTrue(model != null);
	}

	@Test
	public void testValidateCredentials() {
		controller.setModel(new Account());//clear out old model
		//values exist in db for student
		assertTrue(controller.validateCredentials("jsteinberg@ycp.edu", hashSHA256.getHash("password"), "student"));
		//values exist in db for admin
		assertTrue(controller.validateCredentials("mscott@ycp.edu", hashSHA256.getHash("steve"), "admin"));
		//incorrect values
		assertFalse(controller.validateCredentials("notanemail", hashSHA256.getHash("password"), "student"));
		assertFalse(controller.validateCredentials("jsteinberg@ycp.edu", hashSHA256.getHash("WRONG"), "student"));
		assertFalse(controller.validateCredentials("WRONG", hashSHA256.getHash("steve"), "admin"));
		assertFalse(controller.validateCredentials("mscott@ycp.edu", hashSHA256.getHash("wrong"), "admin"));
		assertFalse(controller.validateCredentials("jsteinberg@ycp.edu", hashSHA256.getHash("password"), "notAType"));
	}
}