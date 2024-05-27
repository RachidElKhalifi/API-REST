package com.labAirApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.ColoreDispDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.ColoreDisp;

@Service
public class ColoreDispServiceImp implements ColoreDispService {

	@Autowired
	ColoreDispDao coloreDispDao;
	
	@Override
	public List<ColoreDisp> getColoriDisp() {
		return (List<ColoreDisp>) coloreDispDao.findAll();
	}

	@Override
	public ColoreDisp getColoreDispById(int id) {	
			Optional<ColoreDisp> coloreOptional = coloreDispDao.findById(id);
			if(coloreOptional.isPresent())
				return coloreOptional.get();
			else return null;
		}
	
	@Override
	public ColoreDisp salva(ColoreDisp coloreDisp) {
		return  coloreDispDao.save(coloreDisp);
	}

	@Override
	public ObjectNode cancella(int id) {
		Optional<ColoreDisp> coloreDispOptional = coloreDispDao.findById(id);
		if(coloreDispOptional.isPresent()) {
			coloreDispDao.delete(coloreDispOptional.get());
			ResponseManager man = new ResponseManager(200,"Colore cancellata con successo");
			return  man.getResponse();
		} 
		ResponseManager man = new ResponseManager(404,"Colore non trovata!");
		return man.getResponse();
	}

}
