package pku;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by yangxiao on 2017/11/14. 一个Key-Value的实现
 */
public class DefaultKeyValue implements KeyValue {
	private final HashMap<String, String> kvs = new HashMap<>();
	public Object getObj(String key) {
		return kvs.get(key);
	}
	public HashMap<String, String> getMap() {
		return kvs;
	}
	public DefaultKeyValue put(String key, int value) {
		kvs.put(key, Integer.toString(value));
		return this;
	}

	public DefaultKeyValue put(String key, long value) {
		kvs.put(key, Long.toString(value));
		return this;
	}

	public DefaultKeyValue put(String key, double value) {
		kvs.put(key, Double.toString(value));
		return this;
	}

	public DefaultKeyValue put(String key, String value) {
		kvs.put(key, value);
		return this;
	}

	public int getInt(String key) {
		String value=kvs.get(key);
		if (value==null) {
			return 0;
		}
		return Integer.parseInt(value);
	}

	public long getLong(String key) {
		String value=kvs.get(key);
		if (value==null) {
			return 0L;
		}
		return Long.parseLong(value);
	}

	public double getDouble(String key) {
		String value=kvs.get(key);
		if (value==null) {
			return 0.0d;
		}
		return Double.parseDouble(value);
	}

	public String getString(String key) {
		return (String) kvs.get(key);
	}

	public Set<String> keySet() {
		return kvs.keySet();
	}

	public boolean containsKey(String key) {
		return kvs.containsKey(key);
	}
}
