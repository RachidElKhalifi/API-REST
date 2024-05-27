package com.labAirApi.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="immagini")
public class Immagine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="nome")
	private String nome;

	@OneToOne(mappedBy = "immagine")
	private ScarpaDisp scarpaDisp;

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
	@JsonBackReference
	public ScarpaDisp getScarpa() {
		return scarpaDisp;
	}

	public void setScarpa(ScarpaDisp scarpaDisp) {
		this.scarpaDisp = scarpaDisp;
	}


}