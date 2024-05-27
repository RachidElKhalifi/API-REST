package com.labAirApi.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="ordini_nei_carrelli")
public class OrdineNelCarrello {
	
	@Transient
	String info ="";
		
	public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}
		
	public OrdineNelCarrello(String info) {
			this.info = info;
		}



	//--------------------------------------------------------------------------------------------------------

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ordine")
	private int idOrdine;

	@Column(name = "identificativo_Scarpa")
	private int identificativoScarpa;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "taglia")
	private String taglia;
	
	@Column(name = "colore")
	private String colore;
	
	@Column(name = "prezzo")
	private float prezzo;
	
	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(
	    name = "carrello_ordine",
	    joinColumns = @JoinColumn(name = "ordine_id"),
	    inverseJoinColumns = @JoinColumn(name = "carrello_id")
	)
	@JsonIgnore
	private List<Carrello> listaCarrelli = new ArrayList<>();
	
	@Column(name = "quantita")
	private int quantita;
	
	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public OrdineNelCarrello() {
		super();
	}

	public OrdineNelCarrello(int identificativoScarpa, String nome, String taglia, String colore, float prezzo,int qta) {
		this.identificativoScarpa = identificativoScarpa;
		this.nome = nome;
		this.taglia = taglia;
		this.colore = colore;
		this.prezzo = prezzo;
		this.quantita = qta;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getIdentificativoScarpa() {
		return identificativoScarpa;
	}

	public void setIdentificativoScarpa(int identificativoScarpa) {
		this.identificativoScarpa = identificativoScarpa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public List<Carrello> getListaCarrelli() {
		return listaCarrelli;
	}

	public void setListaCarrelli(List<Carrello> listaCarrelli) {
		this.listaCarrelli = listaCarrelli;
	}
}
