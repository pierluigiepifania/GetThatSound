package it.model;

public class ClientBean {
	public ClientBean(int id, String nome, String cognome, String email, String utente, String ruolo) {
		this.setId(id);
		this.setNome(nome);
		this.setCognome(cognome);
		this.setEmail(email);
		this.setUtente(utente);
		this.setRuolo(ruolo);
	}
	
	//registrazione
	public ClientBean(String nome, String cognome, String email, String utente, String ruolo) {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setEmail(email);
		this.setUtente(utente);
		this.setRuolo(ruolo);
	}
	public ClientBean() {
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String utente;
	private String ruolo;
}
