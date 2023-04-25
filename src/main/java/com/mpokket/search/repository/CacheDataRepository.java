package com.mpokket.search.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mpokket.search.model.CacheData;


@Repository
public interface CacheDataRepository extends CrudRepository<CacheData, String> {
    
	List<CacheData> findByIdContainingIgnoreCase(String keyword);
}
