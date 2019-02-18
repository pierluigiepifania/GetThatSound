package it.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import it.storage.DriverManagerConnectionPool;
import it.utility.Convert;

public class BranoManager {

	private static final String GET_BRANI_UTENTE = "SELECT * FROM brano WHERE `user_id` = ?;";
	private static final String GET_BRANO_BY_ID = "SELECT * FROM brano WHERE `brano_id` = ?;";
	private static final String GET_BRANI = "SELECT * FROM brano";
	private static final String CERCA_BRANO = "SELECT * FROM `brano` WHERE (titolo LIKE ? OR artista LIKE ? OR album LIKE ?)";
	
	private static final String APPROVA_BRANO = "INSERT INTO `brano` SELECT NULL,`titolo`, `artista`, `album`,`data`, `path`, `user_id` FROM `brano_stallo` WHERE brano_stallo_id=?;";
	private static final String UPDATE_BRANO = "UPDATE `brano` SET `titolo`=?, `artista`=?, `album`=?, `user_id`=? WHERE brano_id=?;";
	@SuppressWarnings("unused")
	private static final String DELETE_BRANO = "DELETE FROM `brano` WHERE brano_id=?;";
	
	private static final String SALVA_BRANO = "INSERT INTO `brano_salvato` (`brano_id`, `user_id`) VALUES (?, ?);";
	private static final String CHECK_BRANO_SALVATO = "SELECT * FROM `brano_salvato` WHERE `brano_id`=? AND `user_id`=?;";
	private static final String DELETE_BRANO_SALVATO = "DELETE FROM `brano_salvato` WHERE `brano_id`=? AND `user_id`=?;";
	private static final String GET_BRANI_SALVATI = "SELECT * FROM brano AS c" + 
			" INNER JOIN brano_salvato AS a ON a.brano_id = c.brano_id" + 
			" WHERE a.user_id = ?;";

	private static final String GET_BRANI_STALLO = "SELECT * FROM brano_stallo";
	private static final String UPDATE_BRANO_STALLO = "UPDATE `brano_stallo` SET `titolo`=?, `artista`=?, `album`=?, `path`=?, `user_id`=? WHERE brano_stallo_id=?;";
	private static final String UPLOAD_BRANO_STALLO = "INSERT INTO `brano_stallo` (`titolo`, `artista`, `album`, `path`, `user_id`) VALUES (?, ?, ?, ?, ?)";
	private static final String DELETE_BRANO_STALLO = "DELETE FROM `brano_stallo` WHERE brano_stallo_id=?;";
	
	public static String getHomeBrani() throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		BranoBean temp = null;
		ArrayList <BranoBean> list = new ArrayList<BranoBean>();
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_BRANI);			
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int id = (int) rs.getInt("brano_id");
				String titolo = Convert.underscoreToSpace(rs.getString("titolo"));
				String artista = Convert.underscoreToSpace(rs.getString("artista"));
				String album = Convert.underscoreToSpace(rs.getString("album"));
				String path = rs.getString("path");
				int user_id = (int) rs.getInt("user_id");
				temp = new BranoBean(id, titolo, artista, album, path, user_id);
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
	
	public static String cercaBrano(String param) throws SQLException {
		if(param.equals(null)) return "";
		Connection conn = null;
		PreparedStatement stm = null;
		BranoBean temp = null;
		ArrayList <BranoBean> list = new ArrayList<BranoBean>();
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(CERCA_BRANO);	
			stm.setString(1, "%"+param+"%");
			stm.setString(2, "%"+param+"%");
			stm.setString(3, "%"+param+"%");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int id = (int) rs.getInt("brano_id");
				String titolo = Convert.underscoreToSpace(rs.getString("titolo"));
				String artista = Convert.underscoreToSpace(rs.getString("artista"));
				String album = Convert.underscoreToSpace(rs.getString("album"));
				String path = rs.getString("path");
				int user_id = (int) rs.getInt("user_id");
				temp = new BranoBean(id, titolo, artista, album, path, user_id);
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
		if(!list.isEmpty()) return (new Gson().toJson(list));
		else return "{\"result\": \"null\"}";
	}
	
	public static String getBraniUtente(int s_user_id) throws SQLException {
		if(s_user_id==0) return "";
		Connection conn = null;
		PreparedStatement stm = null;
		BranoBean temp = null;
		ArrayList <BranoBean> list = new ArrayList<BranoBean>();
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_BRANI_UTENTE);		
			stm.setInt(1, s_user_id);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int id = (int) rs.getInt("brano_id");
				String titolo = Convert.underscoreToSpace(rs.getString("titolo"));
				String artista = Convert.underscoreToSpace(rs.getString("artista"));
				String album = Convert.underscoreToSpace(rs.getString("album"));
				String path = rs.getString("path");
				int user_id = (int) rs.getInt("user_id");
				temp = new BranoBean(id, titolo, artista, album, path, user_id);
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
	
	public static BranoBean getBranoById(int id) throws SQLException {
		if(id == 0) {
			System.out.println("null");
			return null;
		}
		Connection conn = null;
		PreparedStatement stm = null;
		BranoBean bean = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_BRANO_BY_ID);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				String titolo = Convert.underscoreToSpace(rs.getString("titolo"));
				String artista = Convert.underscoreToSpace(rs.getString("artista"));
				String album = Convert.underscoreToSpace(rs.getString("album"));
				String path = rs.getString("path");
				int user_id = (int) rs.getInt("user_id");
				bean = new BranoBean(id, titolo, artista, album, path, user_id);
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
	
	public static String getBraniSalvati(int s_user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		BranoBean temp = null;
		ArrayList <BranoBean> list = new ArrayList<BranoBean>();
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_BRANI_SALVATI);		
			stm.setInt(1, s_user_id);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int id = (int) rs.getInt("brano_id");
				String titolo = Convert.underscoreToSpace(rs.getString("titolo"));
				String artista = Convert.underscoreToSpace(rs.getString("artista"));
				String album = Convert.underscoreToSpace(rs.getString("album"));
				String path = rs.getString("path");
				int user_id = (int) rs.getInt("user_id");
				temp = new BranoBean(id, titolo, artista, album, path, user_id);
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
		if(temp == null) return null;
		return (new Gson().toJson(list));
	}
	
	public static String getBraniStallo() throws SQLException {
		Connection conn = null;
		PreparedStatement stm = null;
		BranoBean temp = null;
		ArrayList <BranoBean> list = new ArrayList<BranoBean>();
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(GET_BRANI_STALLO);			
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				int id = (int) rs.getInt("brano_stallo_id");
				String titolo = Convert.underscoreToSpace((rs.getString("titolo")));
				String artista = Convert.underscoreToSpace((rs.getString("artista")));
				String album = Convert.underscoreToSpace((rs.getString("album")));
				int user_id = (int) rs.getInt("user_id");
				String path = rs.getString("path");
				temp = new BranoBean(id, titolo, artista, album, path, user_id);
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

	public static boolean uploadBranoInStallo(BranoBean bb) throws SQLException {
		boolean result=false;
		if(bb == null) {
			System.out.println("null");
			return false;
		}
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(UPLOAD_BRANO_STALLO);
			stm.setString(1, bb.getTitolo());
			stm.setString(2, bb.getArtista());
			stm.setString(3, bb.getAlbum());
			stm.setString(4, bb.getPath());
			stm.setInt(5, bb.getUser_id());
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
	
	public static boolean salvaBrano(int brano_id, int user_id) throws SQLException {
		if(brano_id==0 || user_id==0) return false;
		boolean result=false;
		Connection conn = null;
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm1 = conn.prepareStatement(SALVA_BRANO);
			stm1.setInt(1, brano_id);
			stm1.setInt(2, user_id);
			if(stm1.executeUpdate()==1) result=true;
			conn.commit();
		}finally {
			try {
				if(stm1!= null)
					stm1.close();
				if(stm2!= null)
					stm2.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
			conn.commit();
		}
		return result;
	}
	
	public static boolean eliminaBranoSalvato(int brano_id, int user_id) throws SQLException {
		if(brano_id==0 || user_id==0) return false;
		boolean result=false;
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(DELETE_BRANO_SALVATO);
			stm.setInt(1, brano_id);
			stm.setInt(2, user_id);
			if(stm.executeUpdate()==1) result=true;
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
			conn.commit();
		}
		return result;
	}
	
	public static boolean checkSalvato(int brano_id, int user_id) throws SQLException {
		boolean result=false;
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(CHECK_BRANO_SALVATO);
			stm.setInt(1, brano_id);
			stm.setInt(2, user_id);
			ResultSet rs = stm.executeQuery();
			if(rs.next() && (rs.getInt("user_id") == (user_id)) && (rs.getInt("brano_id") == (brano_id))) result=true;
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
	
	public static boolean approvaBrano(int brano_stallo_id) throws SQLException {
		boolean result=false;

		Connection conn = null;
		PreparedStatement stm1 = null;
		PreparedStatement stm2 = null;

		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm1 = conn.prepareStatement(APPROVA_BRANO);
			stm1.setInt(1, brano_stallo_id);
			stm2 = conn.prepareStatement(DELETE_BRANO_STALLO);
			stm2.setInt(1, brano_stallo_id);
			if(stm1.executeUpdate()==1) result=true;
			stm1.close();
			if(stm2.executeUpdate()==1) result=true;
			stm2.close();
			conn.commit();
		}finally {
			try {				
					stm1.close();
					stm2.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return result;
	}
	
	public static boolean modificaBranoStallo(BranoBean bb) throws SQLException {
		if(bb==null) return false;
		boolean result=false;
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(UPDATE_BRANO_STALLO);
			stm.setString(1, bb.getTitolo());
			stm.setString(2, bb.getArtista());
			stm.setString(3, bb.getAlbum());
			stm.setString(4, bb.getPath());
			stm.setInt(5, bb.getUser_id());
			stm.setInt(6, bb.getId());
			if(stm.executeUpdate()==1) result=true;
			stm.close();
			conn.commit();
		}finally {
			try {				
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return result;
	}
	
	public static boolean modificaBrano(BranoBean bb) throws SQLException {
		boolean result=false;
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(UPDATE_BRANO);
			stm.setString(1, bb.getTitolo());
			stm.setString(2, bb.getArtista());
			stm.setString(3, bb.getAlbum());
			stm.setInt(4, bb.getUser_id());
			stm.setInt(5, bb.getId());
			if(stm.executeUpdate()==1) result=true;
			stm.close();
			conn.commit();
		}finally {
			try {				
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return result;
	}

	public static boolean eliminaBranoInStallo(int brano_stallo_id) throws SQLException {
		if(brano_stallo_id==0) return false;
		boolean result=false;
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = DriverManagerConnectionPool.getConnection();
			stm = conn.prepareStatement(DELETE_BRANO_STALLO);
			stm.setInt(1, brano_stallo_id);
			if(stm.executeUpdate()==1) result=true;
			conn.commit();
		}finally {
			try {
				if(stm!= null)
					stm.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(conn);
			}
			conn.commit();
		}
		return result;
	}
}
