package com.github.davidmoten.guavamini;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

public final class Maps {
    
    private Maps() {
        // prevent instantiation
    }

    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final Function<Integer, Map<Object, Object>> FACTORY_HASHMAP = //
            size -> new HashMap<Object, Object>(size);
    private static final Function<Integer, Map<Object, Object>> FACTORY_TREEMAP = //
            size -> new TreeMap<Object, Object>();
    private static final Function<Integer, Map<Object, Object>> FACTORY_LINKEDHASHMAP = //
            size -> new LinkedHashMap<Object, Object>(size);

    public static <K, V> Map<K, V> empty() {
        return Collections.emptyMap();
    }
            
    public static <K, V> MapBuilder<K, V> put(K key, V value) {
        return hashMap().put(key, value);
    }
            
    public static Builder treeMap() {
        return new Builder(FACTORY_TREEMAP);
    }
    
    public static Builder hashMap() {
        return new Builder(FACTORY_HASHMAP);
    }

    public static Builder linkedHashMap() {
        return new Builder(FACTORY_LINKEDHASHMAP);
    }

    public static Builder factory(
            Function<? super Integer, ? extends Map<Object, Object>> factory) {
        Preconditions.checkNotNull(factory, "factory cannot be null");
        return new Builder(factory);
    }

    public static final class Builder {

        private final Function<? super Integer, ? extends Map<Object, Object>> factory;
        private int initialSize = DEFAULT_INITIAL_SIZE;

        Builder(Function<? super Integer, ? extends Map<Object, Object>> factory) {
            this.factory = factory;
        }

        public Builder initialSize(int initialSize) {
            Preconditions.checkArgument(initialSize > 0, "initialSize must be greater than 0");
            this.initialSize = initialSize;
            return this;
        }

        @SuppressWarnings("unchecked")
        public <K, V> MapBuilder<K, V> put(K key, V value) {
            return new MapBuilder<K, V>((Map<K, V>) factory.apply(initialSize)).put(key, value);
        }
    }

    public static final class MapBuilder<K, V> {
        private final Map<K, V> map;

        MapBuilder(Map<K, V> map) {
            this.map = map;
        }

        public MapBuilder<K, V> put(K key, V value) {
            map.put(key, value);
            return this;
        }

        public Map<K, V> build() {
            return map;
        }

        public Map<K, V> buildImmutable() {
            return Collections.unmodifiableMap(map);
        }
    }

}
