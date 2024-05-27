package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.Carrello;
@Repository
public interface CarrelloDao extends CrudRepository<Carrello, Integer> {

	
}
