package com.labAirApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.CategoriaDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.Categoria;

@Service
public class CategoriaServiceImp implements CategoriaService {

	@Autowired
	CategoriaDao categoriaDao;
	
	@Override
	public List<Categoria> getCategorie() {
		return (List<Categoria>) categoriaDao.findAll();
	}

	@Override
	public Categoria getCategoriaById(int id) {
		Optional<Categoria> categoriaOptional = categoriaDao.findById(id);
		if(categoriaOptional.isPresent())
			return categoriaOptional.get();
		else return null;

	}

	@Override
	public Categoria salva(Categoria categoria) {
		return  categoriaDao.save(categoria);

	}

	@Override
	public ObjectNode cancella(int id) {
		Optional<Categoria> categoriaOptional = categoriaDao.findById(id);
		if(categoriaOptional.isPresent()) {
			categoriaDao.delete(categoriaOptional.get());
			ResponseManager man = new ResponseManager(200,"Categoria cancellata con successo");
			return  man.getResponse();
		} 
		ResponseManager man = new ResponseManager(404,"Categoria non trovata!");
		return man.getResponse();	}

}
