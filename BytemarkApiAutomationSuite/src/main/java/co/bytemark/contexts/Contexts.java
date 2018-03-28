package co.bytemark.contexts;

import java.util.LinkedHashMap;
import java.util.Map;

public class Contexts {
	Map<String, Object> map = null;
	public static Contexts OBJECTGETTER;

	public Map<String, Object> setContext(String key, String value) {

		if (map == null) {
			map = new LinkedHashMap<String, Object>();
			map.put(key, value);
			return map;
		} else {
			map.put(key, value);
			return map;
		}

	}

	public Map<String, Object> setContext(String key, Object value) {

		if (map == null) {
			map = new LinkedHashMap<String, Object>();
			map.put(key, value);
			return map;
		} else {
			map.put(key, value);
			return map;
		}

	}

	public String getContext(String Key) {

		String s1 = (String) map.get(Key);
		return s1;

	}

}
