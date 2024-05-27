package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.ColoreDisp;
@Repository
public interface ColoreDispDao extends CrudRepository<ColoreDisp, Integer> {

	
}
