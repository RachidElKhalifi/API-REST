package com.labAirApi.model;



import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="carrelli")
public class Carrello {

	@Transient
	String info ="";
		
	public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}
		
		public Carrello(String info) {
			this.info = info;
		}
	//--------------------------------------------------------------------------------------------------------

	


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(name="totale")
private float totale=0;

@OneToOne
@JoinColumn(name ="utente_id", referencedColumnName = "id")
//@JsonBackReference(value = "carrello-utente")
private Utente utente;

@ManyToMany(mappedBy = "listaCarrelli"
,cascade = CascadeType.REFRESH      )
@JsonBackReference
private List<OrdineNelCarrello> listaScarpeNelCarrello=new ArrayList<>();

public Utente getUtente() {
	return utente;
}

public void setUtente(Utente utente) {
	this.utente = utente;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public float getTotale() {
	return totale;
}

public void setTotale(float totale) {
	this.totale = totale;
}

public List<OrdineNelCarrello> getListaScarpeNelCarrello() {
	return listaScarpeNelCarrello;
}

public void setListaScarpeNelCarrello(List<OrdineNelCarrello> listaScarpeNelCarrello) {
	this.listaScarpeNelCarrello = listaScarpeNelCarrello;
}

public Carrello() {
	super();
}

}
