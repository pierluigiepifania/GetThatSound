package it.model;

public class BranoBean {
		
	public BranoBean(int id, String titolo, String artista, String album, String path, int user_id) {
		this.setId(id);
		this.setTitolo(titolo);
		this.setArtista(artista);
		this.setAlbum(album);
		this.setPath(path);
		this.setUser_id(user_id);
	}
	public BranoBean(String titolo, String artista, String album, String path, int user_id) {
		this.setTitolo(titolo);
		this.setArtista(artista);
		this.setAlbum(album);
		this.setPath(path);
		this.setUser_id(user_id);
	}
	public BranoBean() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getUrl_image() {
		return url_image;
	}
	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}
	public String getUrl_audio() {
		return url_audio;
	}
	public void setUrl_audio(String url_audio) {
		this.url_audio = url_audio;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	private int id;
	private String titolo;
	private String artista;
	private String album;
	private String url_image;
	private String url_audio;
	private int user_id;
	private String path;

}
