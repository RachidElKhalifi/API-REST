package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.Immagine;
@Repository
public interface ImmagineDao extends CrudRepository<Immagine, Integer> {

	
}
