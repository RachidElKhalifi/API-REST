package com.labAirApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.ImmagineDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.Immagine;

@Service
public class ImmagineServiceImp implements ImmagineService {

	@Autowired
	ImmagineDao immagineDao;
	
	@Override
	public List<Immagine> getImmagini() {
		return (List<Immagine>) immagineDao.findAll();
	}

	@Override
	public Immagine getImmagineById(int id) {
		Optional<Immagine> immagineOptional = immagineDao.findById(id);
		if(immagineOptional.isPresent())
			return immagineOptional.get();
		else return null;
	}

	@Override
	public Immagine salva(Immagine immagine) {
		return  immagineDao.save(immagine);
	}

	@Override
	public ObjectNode cancella(int id) {
		Optional<Immagine> immagineOptional = immagineDao.findById(id);
		if(immagineOptional.isPresent()) {
			immagineDao.delete(immagineOptional.get());
			ResponseManager man = new ResponseManager(200,"Immagine cancellata con successo");
			return  man.getResponse();
		} 
		ResponseManager man = new ResponseManager(404,"Immagine non trovata!");
		return man.getResponse();	
		}

	}


