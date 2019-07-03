package com.github.pukkertje.TCPServer.cache;

import com.github.pukkertje.TCPServer.server.model.NewsItem;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private static ConcurrentHashMap<String, CacheItem> _cache = new ConcurrentHashMap<String, CacheItem>();

    // TTL in millis (~1 hour)
    private final static long TTL = 1000 * 60 * 60;

    public static boolean inCache(String location) {
        if(!_cache.containsKey(location))
            return false;

        // Check if the last time that we updated the cache was before the TTL period. If so return false so that the
        // Requester can update the cache
        Instant lastUpdated = _cache.get(location).getTime();
        return lastUpdated.until(Instant.now(), ChronoUnit.MILLIS) < TTL;
    }

    public static void addCache(String location, ArrayList<NewsItem> items) {
        _cache.put(location, new CacheItem(location, items));
    }

    public static ArrayList<NewsItem> fetch(String location) {
        if(!inCache(location))
            return null;

        return _cache.get(location).getItems();
    }
}
