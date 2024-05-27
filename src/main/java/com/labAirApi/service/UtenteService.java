package com.labAirApi.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.Utente;

public interface UtenteService {

	
	public List<Utente> getUtenti();	
	public Utente getUtenteById(int id);
	public Utente salva(Utente utente);
	public ObjectNode cancella(int id);
	public List<Utente> getUtentiDeiCarrelli();
}
