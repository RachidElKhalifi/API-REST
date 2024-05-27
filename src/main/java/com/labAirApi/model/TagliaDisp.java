package com.labAirApi.model;

import java.util.ArrayList;
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
@Table(name="taglie_disponibili")
public class TagliaDisp {

@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

@Column(name="taglia")
private String taglia;

@ManyToMany(mappedBy = "taglieDisponibili")
@JsonBackReference
private List<ScarpaDisp> listaArt=new ArrayList<>();
	
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTaglia() {
	return taglia;
}

public void setTaglia(String taglia) {
	this.taglia = taglia;
}

public List<ScarpaDisp> getListaArt() {
	return listaArt;
}

public void setListaArt(List<ScarpaDisp> listaArt) {
	this.listaArt = listaArt;
}

}
