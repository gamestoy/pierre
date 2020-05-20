# HTTP/1.1

## Objetivo
Poder interpretar una request HTTP/1.1 y devolver una respuesta que pueda ser interpretada bajo el mismo protocolo. Con las siguientes limitaciones:
* Se da por sentado que la llamada no vendrá con [ningún encoding](https://tools.ietf.org/html/rfc7230#page-35).
* Los [métodos](https://tools.ietf.org/html/rfc7231#section-4.3.1) a interpretar son GET, POST, PUT y DELETE.
* La conexión [no será persistente](https://tools.ietf.org/html/rfc7230#section-6.3).
* Sólo se interpretará el body como texto.

## RFC
La especificaciones de cómo interpretar la request del cliente están en el [RFC correspondiente](https://tools.ietf.org/html/rfc7230#page-19).

## Request
Una vez interpretada, la request debe tener el siguiente formato:

```java
public class HttpRequest {
  private HttpMethod method;
  private URL url;
  private Map<String, List<String>> headers;
  private Optional<String> body;
  private String remoteAddr;

}

public class URL {
  private Protocol protocol;
  private String host;
  private String path;
  private Map<String, String> queryParams;

}

public enum Protocol {
  HTTP
}

public enum HttpMethod {
  OPTIONS,
  GET,
  HEAD,
  POST,
  PUT,
  DELETE,
  TRACE,
  CONNECT
}
```

## Response
El objeto response debe tener el siguiente formato:
```java
public class HttpResponse {
  private HttpStatus status;
  private Map<String, List<String>> headers;
  private Optional<String> body;

}

public enum HttpStatus {
  OK(200, "OK"),
  NO_CONTENT(204, "No Content"),
  BAD_REQUEST(400, "Bad Request"),
  NOT_FOUND(404, "Not Found"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error");

  private int code;
  private String description;

  HttpStatus(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }
}
```

[[Anterior]](https://github.com/gamestoy/pierre/tree/1.1_sockets) 
[[Siguiente]](https://github.com/gamestoy/pierre/tree/1.3_multiple-requests)
[[Capítulo I]](https://github.com/gamestoy/pierre/tree/1_webserver)
