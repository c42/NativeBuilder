package in.c42.nativebuilder;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.*;
import in.c42.nativebuilder.MapUtils;

public class MapUtilsTest {
    @Test
    public void shouldKnowHowToConvertAMapToOneContainingOnlyStringsForKeys(){
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put(1, "ooga");
        hashMap.put(new StringBuilder("foo"), "bar");

        HashMap<String, Object> stringifiedKeys = MapUtils.stringifyKeys(hashMap);
        assertEquals("ooga", stringifiedKeys.get("1"));
        assertEquals("bar", stringifiedKeys.get("foo"));
    }
    
    @Test
    public void shouldKnowHowToMutateAMapToContainOnlyStringsForKeys(){
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put(1, "ooga");
        hashMap.put(new StringBuilder("foo"), "bar");

        Map<Object,Object> stringifiedKeys = MapUtils.stringifyKeysExclamation(hashMap);
        assertSame(stringifiedKeys, hashMap);
        assertEquals(2, hashMap.size());

        assertEquals("ooga", stringifiedKeys.get("1"));
        assertEquals("bar", stringifiedKeys.get("foo"));
    }
}
