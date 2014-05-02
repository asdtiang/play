package org.asdtiang.play;

import java.util.HashMap;
import java.util.Map;

public class DefaultCache implements CacheInterface{
	private Map<String, Object> cache = new HashMap<String, Object>();

	@Override
	public Object get(String key) {
		return cache.get(key);
	}

	@Override
	public void set(String key, Object object) {
		cache.put(key, object);
	}

}
