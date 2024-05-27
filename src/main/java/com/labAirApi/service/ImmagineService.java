package com.labAirApi.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.Immagine;

public interface ImmagineService {

	
	public List<Immagine> getImmagini();	
	public Immagine getImmagineById(int id);
	public Immagine salva(Immagine immagine);
	public ObjectNode cancella(int id); 
}
