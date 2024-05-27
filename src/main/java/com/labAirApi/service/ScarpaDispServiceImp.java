package com.labAirApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.ScarpaDispDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.ScarpaDisp;

@Service
public class ScarpaDispServiceImp implements ScarpaDispService {

	@Autowired
	ScarpaDispDao scarpaDispDao;
	
	@Override
	public List<ScarpaDisp> getScarpe() {
	return (List<ScarpaDisp>) scarpaDispDao.findAll();
	}
	

	@Override
	public ScarpaDisp getScarpaById(int id) {
		Optional<ScarpaDisp> scarpaOptional = scarpaDispDao.findById(id);
		if(scarpaOptional.isPresent())
			return scarpaOptional.get();
		else return null;
	}
	@Override
	public ScarpaDisp salva(ScarpaDisp scarpaDisp) {
		return scarpaDispDao.save(scarpaDisp);

	}

	@Override
	public ObjectNode cancella(int id) {
		Optional<ScarpaDisp> scarpaDispOptional = scarpaDispDao.findById(id);
		if(scarpaDispOptional.isPresent()) {
			scarpaDispDao.delete(scarpaDispOptional.get());
			ResponseManager man = new ResponseManager(200,"Scarpa cancellata con successo");
			return  man.getResponse();
		} 
		ResponseManager man = new ResponseManager(404,"Scarpa non trovata!");
		return man.getResponse();	}
	}


