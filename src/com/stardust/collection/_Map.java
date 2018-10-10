package com.stardust.collection;

import java.util.*;
import java.util.stream.Collectors;

public class _Map<K,V> implements Map<K, V> {
    private int size = 0;

    private final static int DEFAULT_CAPACITY = 10;

    private Entry<K, V>[] entries;

    public _Map() {
        this.entries = new _Entry[DEFAULT_CAPACITY];
    }

    public class _Entry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public _Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }
    }

    private int getIndexByKey(Object key) {
        for (int i=0;i<entries.length;i++) {
            if (key.equals(entries[i].getKey())) return i;
        }
        return -1;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return getIndexByKey(key) != -1;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int index = getIndexByKey(key);
        if (index != -1) entries[index].getValue();
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = getIndexByKey(key);
        if (index != -1) {
            entries[index] = new _Entry(key, value);
        }
        if (entries.length == size) {
            entries = Arrays.copyOf(entries, DEFAULT_CAPACITY * 2);
        }
        entries[size++] = new _Entry(key, value);
        return value;
    }

    @Override
    public V remove(Object key) {
        int index = getIndexByKey(key);

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return Arrays.stream(entries).map(e -> e.getKey()).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return Arrays.stream(entries).map(e -> e.getValue()).collect(Collectors.toSet());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Arrays.stream(entries).collect(Collectors.toSet());
    }
}
