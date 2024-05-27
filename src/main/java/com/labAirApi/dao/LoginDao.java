package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.Login;
@Repository
public interface LoginDao extends CrudRepository<Login, Integer> {

	
}
