package com.mpokket.search.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;


public class SearchRequest {
	@NonNull
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String buildCacheKey(String keyPrefix) {
	    StringBuilder builder = new StringBuilder(keyPrefix);

	    builder.append("-").append(this.content.toUpperCase());

	    return builder.toString();
	}
}
