package com.labAirApi.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.OrdineNelCarrello;

public interface OrdineNelCarrelloService {
	public List<OrdineNelCarrello> getScarpeNelCarrello();	
	public OrdineNelCarrello getScarpaNelCarrelloById(int idScarpaNelCarrello);
	public OrdineNelCarrello salva(OrdineNelCarrello ordineNelCarrello);
	public ObjectNode cancella(int idScarpaNelCarrello); 

}
