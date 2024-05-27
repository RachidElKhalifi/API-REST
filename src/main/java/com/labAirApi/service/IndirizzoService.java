package com.labAirApi.service;

import java.util.List;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.Indirizzo;
public interface IndirizzoService {

	public List<Indirizzo> getIndirizzi();	
	public Indirizzo getIndirizzoById(int idIndirizzo);
	public Indirizzo salva(Indirizzo indirizzo);
	public ObjectNode cancella(int idCarrello); 



}
