package com.labAirApi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="taglia_scarpa")
public class QtaTagliaDisp {

	@Id
	@Column(name="taglia_id")
	private int idTaglia;
	
	
	@Column(name="quantita")
	private int quantita;


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	

}
