package com.labAirApi.model;



import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="Indirizzi")
public class Indirizzo {

	@Transient
//	@JsonIgnore
	String info ="";
		
	public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}
		
		public Indirizzo(String info) {
			this.info = info;
		}
	//--------------------------------------------------------------------------------------------------------

	


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="via")
private String via;

@Column(name="comune")
private String comune;

@Column(name="provincia")
private String provincia;

@Column(name="paese")
private String paese;

@OneToMany (
		cascade = CascadeType.REFRESH,
		fetch = FetchType.LAZY,
		mappedBy = "indirizzo",
		orphanRemoval = false
		)
@JsonBackReference
private List<Utente> listaUtenti = new ArrayList<>();

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getVia() {
	return via;
}

public void setVia(String via) {
	this.via = via;
}

public String getComune() {
	return comune;
}

public void setComune(String comune) {
	this.comune = comune;
}

public String getProvincia() {
	return provincia;
}

public void setProvincia(String provincia) {
	this.provincia = provincia;
}

public String getPaese() {
	return paese;
}

public void setPaese(String paese) {
	this.paese = paese;
}


public List<Utente> getListaUtenti() {
	return listaUtenti;
}

public void setListaUtenti(List<Utente> listaUtenti) {
	this.listaUtenti = listaUtenti;
}

public Indirizzo() {
	super();
}

}
