package com.labAirApi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.labAirApi.model.TagliaDisp;
@Repository
public interface TagliaDispDao extends CrudRepository<TagliaDisp, Integer> {

	
}
