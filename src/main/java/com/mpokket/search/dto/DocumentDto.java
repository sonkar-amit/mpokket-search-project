package com.mpokket.search.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class DocumentDto {
	@NotBlank
	private String title;

	@NotBlank
	private String author;

	@NotBlank
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
