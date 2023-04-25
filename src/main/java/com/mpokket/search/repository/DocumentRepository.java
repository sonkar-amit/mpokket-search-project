package com.mpokket.search.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mpokket.search.dto.SearchRequest;
import com.mpokket.search.model.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
	@Query(value = "select *"
			+ " from document where upper(content) like upper( concat('%', :#{#request.content}, '%') )", nativeQuery = true)
	List<Document> searchDoc(@Param("request") SearchRequest request);
	
	@Transactional
	@Modifying
	@Query(value = "insert into document(title, author, content) values( :#{#document.title}, :#{#document.author}, :#{#document.content} )", nativeQuery = true)
	void saveDocument(@Param("document")Document document);
}
