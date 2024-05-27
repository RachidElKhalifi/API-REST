package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.Utente;
@Repository
public interface UtenteDao extends CrudRepository<Utente, Integer> {

	
}
