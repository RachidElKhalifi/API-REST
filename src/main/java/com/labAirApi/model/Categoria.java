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

@Entity
@Table(name="categorie")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nome")
	private String nome;
	
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

	public List<ScarpaDisp> getListaArticoli() {
		return listaArticoli;
	}

	public void setListaArticoli(List<ScarpaDisp> listaArticoli) {
		this.listaArticoli = listaArticoli;
	}

	@OneToMany (
			cascade = CascadeType.REFRESH,
			fetch = FetchType.LAZY,
			mappedBy = "categoria",
			orphanRemoval = false
	)
	@JsonBackReference
	private List<ScarpaDisp> listaArticoli = new ArrayList<>();
}
