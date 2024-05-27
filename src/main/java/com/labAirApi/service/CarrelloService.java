package com.labAirApi.service;

import java.util.List;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.Carrello;
import com.labAirApi.model.OrdineNelCarrello;
public interface CarrelloService {

	public List<Carrello> getCarrelli();	
	public Carrello getCarrelloById(int idCarrello);
	public Carrello salva(Carrello carrello);
	public ObjectNode cancella(int idCarrello); 
	public ObjectNode aggiungiAlCarrello(int idScarpa, int idColore, int idTaglia, int qta, int idUtente);
	public ObjectNode eliminaScarpaDalCarrello(int idScarpa);
	public OrdineNelCarrello modQtaScarpaNelCarrello(int idScarpa,int qta); 


}
