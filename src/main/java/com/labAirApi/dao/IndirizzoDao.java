package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.labAirApi.model.Indirizzo;

@Repository
public interface IndirizzoDao extends CrudRepository<Indirizzo, Integer> {

	
}
