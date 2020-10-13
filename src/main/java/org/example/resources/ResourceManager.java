package org.example.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.logging.Logger;

public class ResourceManager<K, V> {
  private static Logger logger = Logger.getGlobal();
  private final V defaultValue;
  private final long timeout;
  private final TimeUnit timeUnit;

  private volatile Map<K, CompletableFuture<V>> dataBase = new HashMap<>();

  private final Function<K, CompletableFuture<V>> computeFunction;

  public ResourceManager(V defaultValue, long timeout, TimeUnit timeUnit) {
    this.defaultValue = defaultValue;
    this.timeout = timeout;
    this.timeUnit = timeUnit;
    computeFunction = k -> new CompletableFuture<V>();
  }

  public ResourceManager(V defaultValue, long timeout, TimeUnit timeUnit, Function<K, CompletableFuture<V>> computeFunction) {
    this.defaultValue = defaultValue;
    this.timeout = timeout;
    this.timeUnit = timeUnit;
    this.computeFunction = computeFunction;
  }

  public V get(K key) {
    try {
      return getFuture(key).get(timeout, timeUnit);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      return defaultValue;
    }
  }

  private synchronized CompletableFuture<V> getFuture(K key) {
    return dataBase.computeIfAbsent(key, computeFunction);
  }

  public synchronized void put(K key, V value) {
    CompletableFuture<V> future = dataBase.get(key);
    if (future != null) {
      logger.info("resolving future " + key);
      future.complete(value);
    } else {
      dataBase.put(key, CompletableFuture.completedFuture(value));
    }
  }
}
