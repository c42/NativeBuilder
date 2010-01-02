package in.c42.nativebuilder;

import org.jruby.RubySymbol;
import org.jruby.Ruby;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    public static HashMap<String, Object> stringifyKeys(Map<Object, Object> hashMap) {
        HashMap<String, Object> stringifiedKeys = new HashMap<String, Object>();
        for (Map.Entry<Object, Object> tuple : hashMap.entrySet()) {
            stringifiedKeys.put(tuple.getKey().toString(), tuple.getValue());
        }
        return stringifiedKeys;
    }

    public static Object getUsingStringOrSymbol(String key, Map<Object, Object> map, Ruby runtime){
        Object value = map.get(key);
        if(value == null) {
//            RubySymbol rubySymbol = org.jruby.Ruby.getGlobalRuntime().newSymbol(key);
            RubySymbol rubySymbol = runtime.newSymbol(key);
            value = map.get(rubySymbol);
        }
        return value;
    }

    public static Map<Object, Object> stringifyKeysExclamation(Map<Object, Object> hashMap) {
        HashMap<String, Object> stringifiedMap = stringifyKeys(hashMap);
        hashMap.clear();
        hashMap.putAll(stringifiedMap);
        return hashMap;
    }
}
