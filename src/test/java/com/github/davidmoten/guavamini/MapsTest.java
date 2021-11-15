package com.github.davidmoten.guavamini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.github.davidmoten.junit.Asserts;

public class MapsTest {

    @Test
    public void isUtilityClass() {
        Asserts.assertIsUtilityClass(Maps.class);
    }

    @Test
    public void testHashMapSize1() {
        Map<Integer, String> map = Maps.put(1, "hi").build();
        assertEquals("hi", map.get(1));
        assertEquals(1, map.size());
        assertTrue(map instanceof HashMap);
    }

    @Test
    public void testHashMapSize2() {
        Map<Integer, String> map = Maps.put(1, "hi").put(2, "there").build();
        assertEquals("hi", map.get(1));
        assertEquals("there", map.get(2));
        assertEquals(2, map.size());
    }

    @Test
    public void testHashMapEmpty() {
        Map<Integer, String> map = Maps.empty();
        assertTrue(map.isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testHashMapImmutable() {
        Map<Integer, String> map = Maps.put(1, "hi").buildImmutable();
        assertEquals("hi", map.get(1));
        assertEquals(1, map.size());
        map.put(2, "there");
    }

    @Test
    public void testHashMapWithInitialSize() {
        Map<Integer, String> map = Maps.hashMap().initialSize(4).put(1, "hi").build();
        assertEquals("hi", map.get(1));
        assertEquals(1, map.size());
        assertTrue(map instanceof HashMap);
    }

    @Test
    public void testCustomMapWithInitialSize() {
        Map<Integer, String> map = Maps.factory(size -> new HashMap<Object, Object>(size))
                .initialSize(4).put(1, "hi").build();
        assertEquals("hi", map.get(1));
        assertEquals(1, map.size());
        assertTrue(map instanceof HashMap);
    }

    @Test
    public void testTreeMapSize1() {
        Map<Integer, String> map = Maps.treeMap().put(1, "hi").build();
        assertEquals("hi", map.get(1));
        assertEquals(1, map.size());
        assertTrue(map instanceof TreeMap);
    }

    @Test
    public void testLinkedHashMapMapSize1() {
        Map<Integer, String> map = Maps.linkedHashMap().put(1, "hi").build();
        assertEquals("hi", map.get(1));
        assertEquals(1, map.size());
        assertTrue(map instanceof LinkedHashMap);
    }

}
