package com.labAirApi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.TagliaDispDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.TagliaDisp;

@Service
public class TagliaDispServiceImp implements TagliaDispService {

	@Autowired
	TagliaDispDao tagliaDispDao;
	
	@Override
	public List<TagliaDisp> getTaglieDisp() {
		return (List<TagliaDisp>) tagliaDispDao.findAll();
	}

	@Override
	public TagliaDisp getTagliaDispById(int id) {
		Optional<TagliaDisp> TagliaOptional = tagliaDispDao.findById(id);
		if(TagliaOptional.isPresent()) {
			return TagliaOptional.get();	
			}
		else return null;
	}

	@Override
	public TagliaDisp salva(TagliaDisp tagliaDisp) {
		return  tagliaDispDao.save(tagliaDisp);
	}

	@Override
	public ObjectNode cancella(int id) {
		Optional<TagliaDisp> tagliaDispOptional = tagliaDispDao.findById(id);
		if(tagliaDispOptional.isPresent()) {
			tagliaDispDao.delete(tagliaDispOptional.get());
			ResponseManager man = new ResponseManager(200,"Taglia cancellata con successo");
			return  man.getResponse();
		} 
		ResponseManager man = new ResponseManager(404,"Taglia non trovata!");
		return man.getResponse();
	}

}
