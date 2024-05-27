package com.labAirApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.UtenteDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.Carrello;
import com.labAirApi.model.Utente;

@Service
public class UtenteServiceImp implements UtenteService {

	@Autowired
	UtenteDao utenteDao;
	
	@Autowired
	CarrelloService carrelloService;
	
	@Override
	public List<Utente> getUtenti() {
	return (List<Utente>) utenteDao.findAll();
	}
	
	@Override
	//questo metodo serve per avere tutti gli utenti che hanno già un carrello
	public List<Utente> getUtentiDeiCarrelli() {
		//creo una lista utenti
		List<Utente> utenti= new ArrayList<>();
		//e per ogni carrello presente nel DB,recupero il suo utente e lo aggiungo alla listaUtenti
		for(Carrello carrello :carrelloService.getCarrelli()){
			utenti.add(carrello.getUtente());
		}
	return  utenti;
	}
	

	@Override
	public Utente getUtenteById(int id) {
		Optional<Utente> utenteOptional = utenteDao.findById(id);
		if(utenteOptional.isPresent())
			return utenteOptional.get();
		else return null;
	}
	
	@Override
	public Utente salva(Utente utente) {
		return utenteDao.save(utente);

	}

	@Override
	public ObjectNode cancella(int idUtente) {
		ResponseManager man;
		if (getUtenteById(idUtente) != null) {
        utenteDao.deleteById(idUtente);
		man = new ResponseManager(200, "Utente eliminato con successo");
		return man.getResponse();	
	}
		else {
			man = new ResponseManager(404, "Utente non trovato");
			return man.getResponse();	
		}
	}


}
