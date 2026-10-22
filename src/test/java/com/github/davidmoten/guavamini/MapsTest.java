package com.github.davidmoten.guavamini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
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
    public void testImmutableMapIsIndependentOfBuilderAndMutableMap() {
        Maps.MapBuilder<Integer, String> builder = Maps.put(1, "original");
        Map<Integer, String> immutable = builder.buildImmutable();

        builder.put(1, "changed").put(2, "new");
        builder.build().clear();

        assertEquals(1, immutable.size());
        assertEquals("original", immutable.get(1));
    }

    @Test
    public void testImmutableMapRetainsKeyOrder() {
        Map<Integer, String> map = Maps.linkedHashMap().put(2, "two").put(1, "one").buildImmutable();
        assertEquals(Arrays.asList(2, 1), new ArrayList<Integer>(map.keySet()));
        map = Maps.treeMap().put(2, "two").put(1, "one").buildImmutable();
        assertEquals(Arrays.asList(1, 2), new ArrayList<Integer>(map.keySet()));
    }

    @Test
    public void testImmutableMapRetainsCustomKeySemantics() {
        String first = new String("key");
        String second = new String("key");
        Maps.MapBuilder<String, String> builder = Maps.factory(size -> new IdentityHashMap<Object, Object>(size))
                .put(first, "first").put(second, "second");
        Map<String, String> map = builder.buildImmutable();
        builder.build().clear();

        assertEquals(2, map.size());
        assertEquals("first", map.get(first));
        assertEquals("second", map.get(second));
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

    @Test(expected = IllegalArgumentException.class)
    public void testMapWithInitialSizeInvalid() {
        Maps.hashMap().initialSize(0);
    }

}
