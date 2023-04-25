package com.mpokket.search.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpokket.search.dto.DocumentDto;
import com.mpokket.search.dto.SearchRequest;
import com.mpokket.search.model.CacheData;
import com.mpokket.search.model.Document;
import com.mpokket.search.repository.CacheDataRepository;
import com.mpokket.search.repository.DocumentRepository;

@Service
public class DocumentService {
	@Autowired
	DocumentRepository docRepo;
	
	@Autowired
	CacheDataRepository cacheRepo;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public void addDocument(Document request) {
		cacheRepo.deleteAll();
		docRepo.saveDocument(request);
	}
	
	public List<Document> searchDocument(SearchRequest request)  throws InterruptedException, JsonProcessingException {
		String cacheKey = request.buildCacheKey("searchDoc");
		Optional<CacheData> optionalCacheData = cacheRepo.findById(cacheKey);
		
		// Cache hit
        if (optionalCacheData.isPresent()) {
            String docAsString = optionalCacheData.get().getValue();

            TypeReference<List<Document>> mapType = new TypeReference<List<Document>>() {};
            List<Document> documents = objectMapper.readValue(docAsString, mapType);

            return documents;
        }
        
        // Cache miss
        List<Document> documents = docRepo.searchDoc(request);

        String docAsJsonString = objectMapper.writeValueAsString(documents);
        CacheData cacheData = new CacheData(cacheKey, docAsJsonString);

        cacheRepo.save(cacheData);
        
		return documents;
	}

}
