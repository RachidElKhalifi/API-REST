package com.labAirApi.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.ColoreDisp;

public interface ColoreDispService {

	
	public List<ColoreDisp> getColoriDisp();	
	public ColoreDisp getColoreDispById(int id);
	public ColoreDisp salva(ColoreDisp coloreDisp);
	public ObjectNode cancella(int id); 
}
