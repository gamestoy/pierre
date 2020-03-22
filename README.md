# Servlet

## Objetivo
Desacoplar la lógica de la aplicación de la funcionalidad del servidor:
* Crear un punto de enlace entre el servidor y la aplicación que, recibiendo un objeto que represente una request del usuario, devuelva un objeto que represente la respuesta.
* Implementar la lógica para devolver siempre una respuesta con status 200.


## Servlet
Para abstraer la implementación, vamos a crear lo siguiente en un paquete aparte, no relacionado con el servidor: 
```java
public interface Servlet {
  void dispatch(ServletRequest request, ServletResponse response);
}

public interface ServletRequest {

  String getHost();

  String getMethod();

  Optional<String> getHeader(String name);

  Optional<List<String>> getHeaderValues(String name);

  String getPath();

  Optional<String> getBody();

  Optional<String> getQueryString();

}

public interface ServletResponse {

  int getStatusCode();

  Optional<String> getBody();

  Optional<List<String>> getHeaderValues(String name);

  Optional<String> getHeader(String name);

  void setStatusCode(int statusCode);

  void addHeaderValues(String name, List<String> values);

  void addHeader(String name, String values);

  void setBody(Optional<String> body);
}
```
Servlet deberá ser implementada, a la vez que las clases que usa el servidor como Request y Response deberán implementar ServletRequest y ServletResponse, respectivamente. Dada la naturaleza mutable de las requests y responses de un cliente, particularmente debido a los filtros que veremos en el próximo capítulo, el dispatcher recibe los dos objetos en lugar de recibir sólo uno y devolver un Response.

## JEE
Tanto Servlet como ServletRequest y ServletResponse son parte de Java EE, y es la forma estándar de desacoplar la funcionalidad de una aplicación de la del servidor. Un Servlet es un programa que corre en el contexto de un Web Server, y es definido como una interfaz con un método principal:
```java
public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException;
```
En nuestro caso creamos unas interfaces similares pero con una cantidad de métodos menor, para reducir la complejidad y no tener que implemetar esa funcionalidad que se iría del alcance del proyecto.

[[Anterior]](https://github.com/gamestoy/pierre/tree/1.3_multiple-requests) 
[[Siguiente]](https://github.com/gamestoy/pierre/tree/1.5_filters) 
[[Capítulo I]](https://github.com/gamestoy/pierre/tree/1_webserver)
