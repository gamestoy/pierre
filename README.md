# Multiple requests

## Objetivo
Poder interpretar más de una request en paralelo, configurando cuál es el límite de request que pueden ejecutarse simultáneamente y devolviendo un error 503 (Service Unavailable) cuando se supere ese límite.

## Thread
Java permite la programación concurrente en base al uso de [threads](https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html). Para utilizar un thread primero debemos definir qué es lo que vamos a ejecutar en el mismo, y para esto debemos implementar Runnable:
```java
public class TestRunnable implements Runnable {
  public void run() {
    System.out.println("Hello from a thread!");
  }
}
```

Luego se puede crear una instancia de Thread a la cual se le pasa nuestra implementación de Runnable:
```java
new Thread(new TestRunnable()).start();
```

También se puede crear una subclase de Thread, la cual ya implementa Runnable:
```java
public class TestThread extends Thread {
  public void run() {
    System.out.println("Hello from a thread!");
  }
}
```

## Thread pools
En las aplicaciones generalmente no se crean threads a mano, sino que se usan thread pools para poder regular, entre otras cosas, la cantidad de threads creados.

```
var fixedExecutor = Executors.newFixedThreadPool(10);
var cachedExecutor = Executors.newCachedThreadPool();
var scheduler = Executors.newScheduledThreadPool(1);
```
En este caso se crean tres tipos de thread pool diferentes:
* Fixed: este thread pool usa una cantidad fija de threads. 
* Cached: crea threads a medida que los necesita, y los reutiliza siempre que estén disponibles.
* Scheduled: este thread pool permite ejecutar tareas en forma periódica o dentro de cierto tiempo.

Sin embargo, a veces necesitamos tener mayor control sobre diferentes aspectos del thread pool. Un ThreadPoolExecutor puede crearse con los siguientes parámetros:
```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler)
```

* corePoolSize - la cantidad de threads que el pool siempre tendrá, aún cuando no estén usándose.
* maximumPoolSize - la cantidad máxima de threads que puede tener el pool.
* keepAliveTime y unit - si la cantidad de threads que tiene el pool es mayor al parámetro corePoolSize, cuánto tiempo deben permancer inactivos antes de terminarlos.
* workQueue - define la estructura de datos encargada de manejar las tareas que no pueden ejecutarse.
* handler - Define lo que debe hacerse si la tarea no puede ser ejecutada.

Los thread pools permiten ejecutar tareas de dos tipos, las que devuelven un valor al ejecutarse (Callable) y las que no (Runnable).
```
// Se ejecuta y se espera un resultado
Future<Response> future = executor.submit(() -> getMovieInfo());

// Se ejecuta y no se espera un resultado.
executor.execute(() -> System.out.println("Hi!"));
```
Un objeto de tipo Future reprensenta el resultado de una ejecución que se realizó en forma asincrónica. Para obtener el valor de la ejecución:
```
// Bloquea el thread principal hasta que se resuelva la ejecución
Response resp = future.get();
```


[[Anterior]](https://github.com/gamestoy/pierre/tree/1.2_http11) 
[[Siguiente]](https://github.com/gamestoy/pierre/tree/1.4_servlet) 
[[Capítulo I]](https://github.com/gamestoy/pierre/tree/1_webserver)
