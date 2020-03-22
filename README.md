# Filtros

## Objetivo
Vamos a implementar filtros a en cada llamada del usuario, implementando en este caso:
* Un filtro que mediante configuración permita bloquear las llamadas de usuarios que tengan ciertas ips (incluídos proxies).
* Implementar una funcionalidad que permita ejecutar este filtro, y los que se quieran agregar, en forma secuencial, siendo lo último que se ejecute el servlet.

## Filter
La funcionalidad de Servlet en Java también nos permite interceptar una llamada en el servidor antes de derivarla al servlet correspondiente. Esto nos permite realizar modificaciones a la llamada o al respuesta, ya sea para agregar headers, bloquearlas por temas de seguridad, etc.
En el paquete javax.servlet tenemos interfaz que nos define la forma de interpretar una llamada, que nosotros vamos a crear en nuestro paquete jdk:
```java
public interface Filter {
  public void doFilter(ServletRequest request, ServletResponse response,
              FilterChain chain) throws IOException, ServletException;
}
```
Todo filtro tendrá que implementar esta interfaz, la cual recibe instancias del request y response del usuario, y una instancia de FilterChain, la cual veremos más adelante pero es la encargada de realizar las llamadas a los filtros y al servlet. 
## FilterChain
Esta es otra interfaz que brinda Java para encadenar llamadas a los diferentes filtros:
```java
public interface FilterChain {
    public void doFilter(ServletRequest request, ServletResponse response)
                throws IOException, ServletException;
}
```

Una implementación de esto debería permitir agregar diferentes filtros a ser ejecutados en orden, siendo lo último en la cadena de ejecución el servlet. A su vez, cada filtro, en caso de querer mantener la secuencia, al finalizar deberá ejecutar ese método para poder continuar con el order de llamadas.



[[Anterior]](https://github.com/gamestoy/pierre/tree/1.4_servlet) 
[[Capítulo I]](https://github.com/gamestoy/pierre/tree/1_webserver)
