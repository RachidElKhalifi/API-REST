package com.labAirApi.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.ScarpaDisp;

public interface ScarpaDispService {
	public List<ScarpaDisp> getScarpe();	
	public ScarpaDisp getScarpaById(int id);
	public ScarpaDisp salva(ScarpaDisp scarpaDisp);
	public ObjectNode cancella(int id);
}
