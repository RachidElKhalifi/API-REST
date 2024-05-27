package com.labAirApi.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="modelli_scarpe_disponibili")
public class ScarpaDisp {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="nome")
private String nome;

@Column(name="descrizione")
private String descrizione;

@Column(name="prezzo")
private float prezzo;

@OneToOne
@JoinColumn(name ="immagine_id", referencedColumnName = "id")
private Immagine immagine;

@ManyToOne (cascade = CascadeType.REFRESH,fetch =  FetchType.EAGER)
@JoinColumn(name = "categoria_id", referencedColumnName = "id")
private Categoria categoria;

@ManyToMany 
@JoinTable(
	    name = "colore_scarpa",
	    joinColumns = @JoinColumn(name = "scarpa_id"),
	    inverseJoinColumns = @JoinColumn(name = "colore_id")
	    )
private List<ColoreDisp> coloriDisponibili = new ArrayList<>();

@ManyToMany
@JoinTable(
    name = "taglia_scarpa",
    joinColumns = @JoinColumn(name = "scarpa_id"),
    inverseJoinColumns = @JoinColumn(name = "taglia_id")
)
private List<TagliaDisp> taglieDisponibili = new ArrayList<>();

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

public Categoria getCategoria() {
	return categoria;
}

public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}

public float getPrezzo() {
	return prezzo;
}

public void setPrezzo(float prezzo) {
	this.prezzo = prezzo;
}

public String getDescrizione() {
	return descrizione;
}

public void setDescrizione(String descrizione) {
	this.descrizione = descrizione;
}

public Immagine getImmagine() {
	return immagine;
}

public void setImmagine(Immagine immagine) {
	this.immagine = immagine;
}

public List<TagliaDisp> getTaglieDisponobili() {
	return taglieDisponibili;
}

public void setTaglieDisponobili(List<TagliaDisp> taglieDisponobili) {
	this.taglieDisponibili = taglieDisponobili;
}

public List<ColoreDisp> getColoriDisponibili() {
	return coloriDisponibili;
}

public void setColoriDisponibili(List<ColoreDisp> coloriDisponibili) {
	this.coloriDisponibili = coloriDisponibili;
}

//public String getColore() {
//	return colore;
//}
//
//public void setColore(String colore) {
//	this.colore = colore;
//}

//public String getTaglia() {
//	return taglia;
//}
//
//public void setTaglia(String taglia) {
//	this.taglia = taglia;
//}



//public List<Carrello> getDentroAiCarrelli() {
//	return listaCarrelli;
//}
//
//public void setDentroAiCarrelli(List<Carrello> dentroAiCarrelli) {
//	this.listaCarrelli = dentroAiCarrelli;
//}

//@ManyToMany 
//@JoinTable(
//	    name = "ordini",
//	    joinColumns = @JoinColumn(name = "scarpa_id"),
//	    inverseJoinColumns = @JoinColumn(name = "carrello_id")
//	    )
//private List<Carrello> listaCarrelli = new ArrayList<>();


//public ScarpeDisp() {
//	super();
//}
//
//
//public ScarpeDisp(int id,String nome,Categoria categoria, float prezzo, String colore, String taglia) {
//  this.id = id;
//  this.nome = nome;
//  this.categoria = categoria;
//  this.prezzo = prezzo;
//  this.colore = colore;
//  this.taglia = taglia;
//  this.prezzo = prezzo;
//}

//@ManyToOne (cascade = CascadeType.REFRESH,fetch =  FetchType.EAGER)
//@JoinColumn(name = "ordine_id", referencedColumnName = "id")
//private OrdineNelCarrello ordine;
//
//public OrdineNelCarrello getOrdine() {
//	return ordine;
//}
//
//
//public void setOrdine(OrdineNelCarrello ordine) {
//	this.ordine = ordine;
//}



//@Column(name = "ordine_id")
//private int ordineId;
//
////Getter e setter per ordineId
//public int getOrdineId() {
//  return ordineId;
//}
//
//public void setOrdineId(int ordineId) {
//  this.ordineId = ordineId;
//}



}
