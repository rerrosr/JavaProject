package com.example.univerproject.test_cache;

import com.example.univerproject.cache.MyCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMyCache {

    private MyCache<String, Integer> cache;

    @BeforeEach
    public void setUp() {
        cache = new MyCache<>(3);
    }

    @Test
    void testPutAndGet() {
        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);

        Assertions.assertEquals(Integer.valueOf(1), cache.get("key1"));
        Assertions.assertEquals(Integer.valueOf(2), cache.get("key2"));
        Assertions.assertEquals(Integer.valueOf(3), cache.get("key3"));
    }

    @Test
    void testGetNonExistentKey() {
        Assertions.assertNull(cache.get("nonexistent"));
    }

    @Test
    void testRemove() {
        cache.put("key1", 1);
        cache.remove("key1");

        Assertions.assertNull(cache.get("key1"));
    }

    @Test
    void testClear() {
        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.clear();

        Assertions.assertNull(cache.get("key1"));
        Assertions.assertNull(cache.get("key2"));
    }
}