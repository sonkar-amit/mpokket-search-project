package com.mpokket.search.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mpokket.search.dto.DocumentDto;
import com.mpokket.search.dto.SearchRequest;
import com.mpokket.search.model.Document;
import com.mpokket.search.service.DocumentService;
import com.mpokket.search.util.StringUtil;
import com.mpokket.search.util.ValidationException;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
	@Autowired
	DocumentService docService;
	
	@Autowired
	StringUtil util;
	
	@PostMapping
	void addDocument(@Valid @RequestBody Document request) {
		try {
			util.isNotNullAndEmpty(request.getTitle(), "Title can not be null/empty");
			util.isNotNullAndEmpty(request.getAuthor(), "Author can not be null/empty");
			util.isNotNullAndEmpty(request.getContent(), "Content can not be null/empty");
			docService.addDocument(request);
		} catch(ValidationException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/search")
	List<Document> searchDocument(SearchRequest request) {
		if(request.getContent() == null || request.getContent().isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Search content should not be null/empty.");
		}
		
		try {
			return docService.searchDocument(request);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
}
