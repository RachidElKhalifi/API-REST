package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.QtaTagliaDisp;
@Repository
public interface QtaTagliaDispDao extends CrudRepository<QtaTagliaDisp, Integer> {

	
}
