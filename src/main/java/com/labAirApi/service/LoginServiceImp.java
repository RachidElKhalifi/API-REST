package com.labAirApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.LoginDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.Login;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
    private LoginDao loginDao;
	
	public Login controlloLogin(Login login) {
		//ricupero la lista di tutti i login presenti nel DB
		List<Login> listaLogin=(List<Login>) loginDao.findAll();
        //ciclo sulla lista per controllare se i dati passati combaciano con qualche utente nel DB
		for(Login item : listaLogin) {
		if(login.getUsername().equals(item.getUsername()) && login.getPassword().equals(item.getPassword())) {
			return item;
			}
        }
		 return null;
	}

	@Override
	public ObjectNode salva(Login login) {
		loginDao.save(login);		
		ResponseManager	man = new ResponseManager(200, "Dati salvati con successo");
		return man.getResponse();	
	}

}
