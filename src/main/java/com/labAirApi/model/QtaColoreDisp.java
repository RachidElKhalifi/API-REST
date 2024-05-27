package com.labAirApi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="colore_scarpa")
public class QtaColoreDisp {

	@Id
	@Column(name="colore_id")
	private int idColore;
	
	@Column(name="quantita")
	private int quantita;

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	

	
	

}
