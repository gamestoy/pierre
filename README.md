# Pierre Menard, autor de Spring

<p align="center">
    <img width="500" src="https://github.com/gamestoy/pierre/blob/master/image/quixote.jpg?raw=true" />
</p>

> El método inicial que imaginó era relativamente sencillo. Conocer bien el español, recuperar la fe católica, guerrear contra los moros o contra el turco, olvidar la historia de Europa entre los años de 1602 y de 1918, ser Miguel de Cervantes. Pierre Menard estudió ese procedimiento (sé que logró un manejo bastante fiel del español del siglo diecisiete) pero lo descartó por fácil. ¡Más bien por imposible! dirá el lector. De acuerdo, pero la empresa era de antemano imposible y de todos los medios imposibles para llevarla a término, éste era el menos interesante. Ser en el siglo veinte un novelista popular del siglo diecisiete le pareció una disminución. Ser, de alguna manera, Cervantes y llegar al Quijote le pareció menos arduo -por consiguiente, menos interesante- que seguir siendo Pierre Menard y llegar al Quijote, a través de las experiencias de Pierre Menard.
>
> -Pierre Menard, autor del Quijote.

## Objetivo
La idea de este proyecto es intentar reproducir algunas de las funcionalidades de Spring. No se trata de cubrir todos los aspectos de cada una de ellas, sino de cubrir lo mínimo indispensable para entender los conceptos detrás del framework que lo hacen prácticamente indistinguible de la magia.

Dejando eso en claro, la librería que vamos a crear debe permitir el uso de una sintáxis como esta:

```java
@RestController("/test")
public class TestController {

  @GetMapping("/{id}")
  public Test getTest(
      @PathVariable("id") Integer id,
      @RequestParam(value = "options", required = false) String options,
      @RequestHeader(value = "x-refresh", required = false) Boolean refresh) {
    return new Test(id, "name");
  }

  @GetMapping("/{test_id}/case/{case_id}")
  public EntityResponse<String> getWithPathVariables(
      @PathVariable("case_id") String id,
      @PathVariable("test_id") String testId,
      @RequestParam(value = "options", required = false) Integer options) {
    return new EntityResponse<>(HttpStatus.OK, "test_id: " + testId + "case_id: " + id);
  }

  @PostMapping("")
  public EntityResponse<String> getEntity(@RequestBody Test test) {
    return new EntityResponse<>(
        HttpStatus.OK, String.format("id: %s name: %s", test.getId(), test.getName()));
  }

  @PutMapping("/{id}")
  public Test returnJsonObject(@PathVariable("id") Integer id) {
    return new Test(id, "test" + id);
  }

  @DeleteMapping("/{id}")
  public String deleteTest(@PathVariable("id") String id) {
    return id;
  }

  @PostMapping("/{id}/list")
  public @ResponseBody String getListByTestId(
      @RequestBody List<String> ids, @PathVariable("id") String id) {
    return "id: " + id + " ids: " + ids.toString();
  }
}
```

Además, internamente debe poder:
* Recibir una llamada HTTP/1.1 e interpretarla.
* Crear controllers a los cuales derivar la llamada en base al método y el path del request, obteniendo los diferentes parámetros necesarios para su ejecución.
* Serializar y deserializar los diferentes media types.
* Permitir la inyección de dependencias.

## Índice
1. [Capítulo I - Web server](https://github.com/gamestoy/pierre/tree/1_webserver)

