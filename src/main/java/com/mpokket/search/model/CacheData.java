package com.mpokket.search.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

//@Accessors(chain = true)
@RedisHash("cacheData")
public class CacheData {
	@Id
    private String key;

    @Indexed
    private String value;
    
    public CacheData(String key, String value) {
    	this.key = key;
    	this.value = value;
    }
    
    public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}