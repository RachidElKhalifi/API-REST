package com.labAirApi.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.Categoria;

public interface CategoriaService {

	
	public List<Categoria> getCategorie();	
	public Categoria getCategoriaById(int id);
	public Categoria salva(Categoria categoria);
	public ObjectNode cancella(int id); 
}
