package org.asdtiang.play;

public interface CacheInterface {

	Object get(String key);
	void set(String key,Object object);
}
