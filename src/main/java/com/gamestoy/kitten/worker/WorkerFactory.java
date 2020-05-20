package com.gamestoy.kitten.worker;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkerFactory {

  public ExecutorService create(int workers, Duration keepAlive) {
    return new ThreadPoolExecutor(
        workers, workers, keepAlive.toMillis(), TimeUnit.MILLISECONDS, new SynchronousQueue<>());
  }

}
