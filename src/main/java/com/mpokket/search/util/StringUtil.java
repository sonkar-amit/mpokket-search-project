package com.mpokket.search.util;

import javax.persistence.Entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Configuration
public class StringUtil {
	public boolean isNotNullAndEmpty(String value, String message)throws ValidationException {
		if(value == null || value.trim().length() <= 0) {
			throw new ValidationException(message);
		}
		
		return true;
	}
}
