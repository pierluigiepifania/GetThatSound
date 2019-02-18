package it.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import it.storage.DriverManagerConnectionPool;
import it.utility.PasswordAuthentication;

public class ClientManager{

	private static final String REGISTER = "INSERT INTO `utente` (`utente`, `password`, `nome`, `cognome`, `email`, `ruolo`) VALUES (?, ?, ?, ?, ?, ?);";
	private static final String CHECK_USER = "SELECT * FROM utente WHERE `utente` LIKE ?;";
	private static final String GET_USER_BY_ID = "SELECT * FROM utente WHERE `user_id` = ?;";
	private static final String GET_USER_BY_UTENTE = "SELECT * FROM utente WHERE `utente` = ?;";
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM utente WHERE `email` = ?;";
	private static final String GET_UTENTI = "SELECT * FROM utente";	
	private static final String SET_UTENTE_MANAGER = "UPDATE `utente` SET `ruolo` = 'manager' WHERE `utente`.`user_id` = ?;";
	private static final String SET_UTENTE_STANDARD = "UPDATE `utente` SET `ruolo` = 'standard' WHERE `utente`.`user_id` = ?;";
	private static final String DELETE_UTENTE = "DELETE * FROM utente WHERE `user_id` = ?;";
	
	public static String getUtenti() throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		ClientBean temp = null;
		ArrayList<ClientBean> list = new ArrayList<ClientBean>();
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_UTENTI);			
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int id = (int) rs.getInt("user_id");
				String utente = rs.getString("utente");
				String nome = (rs.getString("nome"));
				String cognome = (rs.getString("cognome"));
				String email = rs.getString("email");
				String ruolo = rs.getString("ruolo");
				temp = new ClientBean(id, nome, cognome, email, utente, ruolo);
				list.add(temp);
			}
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return (new Gson().toJson(list));
	}
	
	@SuppressWarnings("deprecation")
	public static ClientBean checkOnDb(String utente, String login_password) throws SQLException {
		if(utente.equals(null) || login_password.equals(null)) return null;
		Connection conn = null;
		PreparedStatement stm = null;
		ClientBean bean = null;
		PasswordAuthentication pa = new PasswordAuthentication();
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(CHECK_USER);
			stm.setString(1, utente);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("utente");
				String password = rs.getString("password");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String ruolo = rs.getString("ruolo");
				if(pa.authenticate(login_password, password)) bean = new ClientBean(id,nome,cognome,email,username,ruolo);
				else return null;
			}
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return bean;
	}

	public static ClientBean getClientById(int id) throws SQLException {
		if(id == 0) return null;
		Connection conn = null;
		PreparedStatement stm = null;
		ClientBean bean = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_USER_BY_ID);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				String username = rs.getString("utente");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String ruolo = rs.getString("ruolo");
				bean = new ClientBean(id,nome,cognome,email,username,ruolo);
			}
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return bean;
	}
	
	public static boolean isUtente(String user) throws SQLException {
		if(user.equals(null)) return false;
		Connection conn = null;
		PreparedStatement stm = null;
		boolean result = false;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_USER_BY_UTENTE);
			stm.setString(1, user);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) result = true;
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return result;
	}
	
	public static boolean isUtenteEmail(String email) throws SQLException {
		if(email.equals(null)) return false;
		Connection conn = null;
		PreparedStatement stm = null;
		boolean result = false;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_USER_BY_EMAIL);
			stm.setString(1, email);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) result = true;
			stm.close();
			rs.close();
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return result;
	}

	public static int setClientTo(int id, int caso) throws SQLException {
		if(id==0 || caso==0) return 0;
		Connection conn = null;
		PreparedStatement stm = null;
		int result = 0;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			switch(caso) {
				case(1): {
					stm = conn.prepareStatement(SET_UTENTE_STANDARD);
					break;
				}
				case(2): {
					stm = conn.prepareStatement(SET_UTENTE_MANAGER);
					break;
				}
				case(4): {
					stm = conn.prepareStatement(DELETE_UTENTE);
					break;
				}
			}
			stm.setInt(1, id);
			result = stm.executeUpdate();
			stm.close();
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return result;
	}

	public static boolean registraUtente(ClientBean cb, String token) throws SQLException {
		boolean result=false;
		if(cb == null || token.equals(null)) return false;
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(REGISTER);
			stm.setString(1, cb.getUtente());
			stm.setString(2, token);
			stm.setString(3, cb.getNome());
			stm.setString(4, cb.getCognome());
			stm.setString(5, cb.getEmail());
			stm.setString(6, cb.getRuolo());
			if(stm.executeUpdate()==1) result=true;
			stm.close();
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return result;
	}
}
