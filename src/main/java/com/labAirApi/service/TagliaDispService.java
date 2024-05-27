package com.labAirApi.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.TagliaDisp;

public interface TagliaDispService {

	
	public List<TagliaDisp> getTaglieDisp();	
	public TagliaDisp getTagliaDispById(int id);
	public TagliaDisp salva(TagliaDisp tagliaDisp);
	public ObjectNode cancella(int id); 
}
