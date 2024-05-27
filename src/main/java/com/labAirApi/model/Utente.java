package com.labAirApi.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="utenti")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	@Column(name="email")
	private String email;

	@OneToOne(mappedBy = "utente")
	@JsonIgnore
	private Carrello carrello;
	
	@OneToOne(mappedBy = "utente")
	@JsonIgnore
	private Login login;
	
	@ManyToOne (cascade = CascadeType.REFRESH,fetch =  FetchType.EAGER)
	@JoinColumn(name = "indirizzo_id", referencedColumnName = "id")
	@JsonIgnore
    private Indirizzo indirizzo;
	
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	

}
