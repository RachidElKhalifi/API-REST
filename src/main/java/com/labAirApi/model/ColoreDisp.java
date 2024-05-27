package com.labAirApi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="colori_disponibili")
public class ColoreDisp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="colore")
	private String nome;
	
	@ManyToMany(mappedBy = "coloriDisponibili")
	@JsonBackReference

	private List<ScarpaDisp> listaScarpe;

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
//	@JsonBackReference
	public List<ScarpaDisp> getListaScarpe() {
		return listaScarpe;
	}

	public void setListaScarpe(List<ScarpaDisp> listaScarpe) {
		this.listaScarpe = listaScarpe;
	}

	
}
