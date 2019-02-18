package test.model;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import it.model.ClientBean;
import it.model.ClientManager;
import it.utility.PasswordAuthentication;

class ClientManagerTest {

	private static ClientManager clientManager;
	Gson gson = new Gson();

	static {
		clientManager = new ClientManager();
	}
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }
	@AfterEach
    public void tearDown() throws Exception{
		DatabaseHelper.initializeDatabase();
    }
	@SuppressWarnings("static-access")
	@Test
	void TestGetClientById() throws JsonSyntaxException, SQLException {
		ClientBean cb_result = clientManager.getClientById(1);
		Assert.assertEquals("molliadriano@gmail.com", cb_result.getEmail());

	}
	@SuppressWarnings("static-access")
	@Test
	void TestGetUtenti() throws JsonSyntaxException, SQLException {
		ClientBean[] cb_list = gson.fromJson(clientManager.getUtenti(), ClientBean[].class);
		Assert.assertNotNull(cb_list);
		Assert.assertEquals("Adriano", cb_list[0].getNome());
		Assert.assertEquals("Luigi", cb_list[1].getNome());		
		Assert.assertEquals("Claudio", cb_list[2].getNome());		
		Assert.assertEquals("Dario", cb_list[3].getNome());		
	}
	@SuppressWarnings("static-access")
	@Test
	void TestCheckOnDb() throws JsonSyntaxException, SQLException {
		ClientBean cb_result = clientManager.checkOnDb("adriano", "adriano");
		Assert.assertEquals("Adriano", cb_result.getNome());
		Assert.assertEquals("Molli", cb_result.getCognome());
		Assert.assertEquals("molliadriano@gmail.com", cb_result.getEmail());
	}
	@SuppressWarnings({ "static-access", "deprecation" })
	@Test
	void TestRegistraUtente() throws JsonSyntaxException, SQLException {
		ClientBean cb = new ClientBean("Nome", "Cognome", "email@example.it", "user", "standard");
		PasswordAuthentication pa = new PasswordAuthentication();
		String token = pa.hash("prova");
		clientManager.registraUtente(cb, token);
		ClientBean cb_result = clientManager.checkOnDb("user", "prova");
		Assert.assertEquals("Nome", cb_result.getNome());
		Assert.assertEquals("Cognome", cb_result.getCognome());
		Assert.assertEquals("email@example.it", cb_result.getEmail());
	}
	@SuppressWarnings({ "static-access", "deprecation" })
	@Test
	void TestSetClientTo() throws JsonSyntaxException, SQLException {
		ClientBean cb = new ClientBean("Nome", "Cognome", "email@example.it", "user", "standard");
		PasswordAuthentication pa = new PasswordAuthentication();
		String token = pa.hash("prova");
		clientManager.registraUtente(cb, token);
		clientManager.setClientTo(5, 2);
		ClientBean cb_result = clientManager.checkOnDb("user", "prova");
		Assert.assertEquals("manager", cb_result.getRuolo());
	}
}