# Sockets

## Objetivo
Crear un servidor que acepte una conexión y lea la primer línea de la llamada, usando el método read, e imprimiéndola por consola. Tener en cuenta que la request del cliente puede no tener un delimitador de salto de línea, en cuyo caso debemos leer todo el mensaje.

## Sockets
Un socket es un endpoint definido por una dirección IP y un puerto, en el contexto de una conexión TCP. Una conexión TCP está definida por la existencia de dos endpoints, por lo tanto de dos sockets.
## Server
Para crear un server basta con instanciar la clase ServerSocket:
```java
var server = new ServerSocket(port);
```

Una vez que el server está escuchando en ese puerto, debemos obtener un socket cuando se establezca una conexión:
```java
var socket = server.accept();
```
El método accept se queda bloqueado hasta que se establezca una conexión, y una vez que es establecida nos da un socket para poder implementar nuestra funcionalidad. Este socket tiene en principio dos componentes principales: un InputStream, que representa un stream de bytes de lectura, y un OutputReader, un stream de bytes para escritura.
Estas abstracciones nos permiten leer y escribir bytes, en el primer caso la request del cliente y en el segundo la respuesta que le vamos a dar.

Un InputStream permite leer el próximo byte del stream, o usar un buffer de bytes que permite leer una mayor cantidad. Ambos métodos devuelven -1 cuando se llegó al final del stream:
```java
int byteValue = inputStream.read();
byte[] data = new byte[1024];

inputStream.read(data);
```

Con esto es posible lograr el objetivo, hay que tener en cuenta que un stream no es una lista, no se puede retroceder para volver a leer lo ya leído. También hay que tener en cuenta que hasta que la conexión no se cierra, el stream puede seguir recibiendo datos y por lo tanto no devuelve el valor de terminación.

[[Siguiente]](https://github.com/gamestoy/pierre/tree/1.2_http11)
[[Capítulo I]](https://github.com/gamestoy/pierre/tree/1_webserver)
