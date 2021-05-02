package com.test.algorithm;

import java.util.ArrayList;

/**
 * @Author: movie
 * @Date: 2020/12/27 23:34
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int defaultInitSize;
    private float defaultLoadFactor;
    private int entryUseSize;
    private Entry<K, V>[] table;


    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitSize, float defaultLoadFactor) {
        if (defaultInitSize < 0) {
            throw new IllegalArgumentException("illegal inital capcity:" + defaultInitSize);
        }
        if (defaultLoadFactor <= 0 || Float.isNaN(defaultLoadFactor)) {
            throw new IllegalArgumentException("illegal load factor:" + defaultLoadFactor);
        }
        this.defaultInitSize = defaultInitSize;
        this.defaultLoadFactor = defaultLoadFactor;
        table = new Entry[defaultInitSize];
    }

    @Override
    public V put(K k, V v) {
        V oldValue = null;
        if (entryUseSize > defaultInitSize * defaultLoadFactor) {
            resize(defaultInitSize * 2);
        }
        int index = hash(k) & (defaultInitSize - 1);
        if (table[index] == null) {
            table[index] = new Entry<>(k, v, null);
        } else {
            Entry<K, V> e = table[index];
            while (e != null) {
                if (k == e.getKey() || k.equals(e.getKey())) {
                    oldValue = e.value;
                    e.value = v;
                    return oldValue;
                }
                e = e.next;
            }
            table[index] = new Entry<>(k, v, e);
        }
        entryUseSize++;
        return oldValue;
    }

    private int hash(K k) {
        int hashCode = k.hashCode();
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private void resize(int i) {
        Entry[] newTable = new Entry[i];
        defaultInitSize = i;
        entryUseSize = 0;
        rehash(newTable);
    }

    private void rehash(Entry<K, V>[] newTable) {
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                entries.add(entry);
                entry = entry.next;
            }
        }
        if (newTable.length > 0) {
            table = newTable;
        }
        entries.forEach(e -> put(e.getKey(), e.getValue()));
    }


    @Override
    public V get(K k) {
        int index = hash(k) & (defaultInitSize - 1);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            return null;
        }
        while (entry != null) {
            if (k == entry.getKey() && k.equals(entry.getKey())) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }


    class Entry<K, V> implements MyMap.Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ":" + map.get(i));
        }
    }

}
