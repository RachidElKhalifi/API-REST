package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.OrdineNelCarrello;
@Repository
public interface OrdineNelCarrelloDao extends CrudRepository<OrdineNelCarrello, Integer> {

	
}
