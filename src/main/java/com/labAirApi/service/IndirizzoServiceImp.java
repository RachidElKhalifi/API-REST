package com.labAirApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.IndirizzoDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.Indirizzo;

@Service
public class IndirizzoServiceImp implements IndirizzoService {

	@Autowired
	IndirizzoDao indirizzoDao;
	
	@Override
	public List<Indirizzo> getIndirizzi() {
		return (List<Indirizzo>) indirizzoDao.findAll();

	}

	@Override
	public Indirizzo getIndirizzoById(int idIndirizzo) {
		Optional<Indirizzo> indirizzoOptional = indirizzoDao.findById(idIndirizzo);
		if(indirizzoOptional.isPresent())
			return indirizzoOptional.get();
		else return null;
	}

	@Override
	public Indirizzo salva(Indirizzo indirizzo) {
		return  indirizzoDao.save(indirizzo);

	}

	@Override
	public ObjectNode cancella(int idIndirizzo) {
		Optional<Indirizzo> indirizzoOptional = indirizzoDao.findById(idIndirizzo);
		if(indirizzoOptional.isPresent()) {
			indirizzoDao.delete(indirizzoOptional.get());
			ResponseManager man = new ResponseManager(200,"Indirizzo cancellato con successo");
			return  man.getResponse();
		} 
		ResponseManager man = new ResponseManager(404,"Indirizzo non trovato!");
		return man.getResponse();
		}
	}


