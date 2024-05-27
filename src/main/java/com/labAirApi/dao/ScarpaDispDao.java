package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.ScarpaDisp;
@Repository
public interface ScarpaDispDao extends CrudRepository<ScarpaDisp, Integer> {

	
}
