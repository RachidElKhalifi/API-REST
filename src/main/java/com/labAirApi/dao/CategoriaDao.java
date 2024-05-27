package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.Categoria;
@Repository
public interface CategoriaDao extends CrudRepository<Categoria, Integer> {

	
}
