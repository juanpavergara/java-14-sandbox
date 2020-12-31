package com.s4n.sandbox.records;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RecordsTest {
  @Test
  @DisplayName("Se debe poder construir un Record")
  void useTheBasicRecord() {
    var mr = new MyRecord("a","b",0);
    assertEquals("a", mr.a());
    assertEquals("b", mr.b());
    assertEquals(0, mr.c());
  }
  @Test
  @DisplayName("Se debe poder operar sobre Records en un algebra")
  void passARecordToAnOp() {
    /*
    lo más interesante (IMO) que trae los records es la capacidad de pensar
    en diseño alegebraico. Esto es: separar los sustantivos de los verbos de un dominio.
    O en términos de DDD: separar las Entidades y Agregados de los Servicios (de dominio)
    Usando Records para implementar sustantivos se tiene como implicación el diseño de álgebras
    que operen sobre esos sustantivos. Es el primer paso para dejar atrás el diseño con tipos de
    datos abstractos (ADT), es decir, como clases que agrupan tanto estructura física (atributos)
    y estructura lógica (métodos).
    La definición de ADTs tradicionales la encuentran en:
    http://web.cs.iastate.edu/~hridesh/teaching/362/07/01/papers/p50-liskov.pdf

    Información oficial sobre Records en Java se encuentra en:
    https://openjdk.java.net/jeps/359
     */
    var mr1 = new MyRecord("a","b",0);
    var mr2 = new MyRecord("a","b",2);
    var mr3 = MyAlgebra.add(mr1, mr2);

    assertEquals("aa", mr3.a());
    assertEquals("bb", mr3.b());
    assertEquals(2, mr3.c());

  }

  @Test
  @DisplayName("Se debe poder hacer instanceof sobre un Record")
  void instanceOfOnARecord() {

    /*
    Con esta nueva capacidad ya no hay que hacer cast al tipo deseado cuando una verificación
    de instanceOf es true.

    La variable `mr1` es el `target`
    La porción `MyRecord mri` es el `type test pattern` o el `predicate`
    El operador `instanceof` hace el `matching` del target contra el type test pattern
    `mri` el el binding variable la cual se extraerá desde el `target` solo si el `predicate` aplica.

    Si bien el JSR indica que las target variables pueden ser un conjunto (es decir, más de una)
    no es claro cómo se puede lograr esto.

    Información oficial sobre esta capacidad se encuentra en:
    https://openjdk.java.net/jeps/305
     */

    var mr1 = new MyRecord("a","b",0);
    if(mr1 instanceof MyRecord mri){
      assertEquals(mri.a(), "a");
    }else{
      assertTrue(false);
    }
  }

  @Test
  @DisplayName("Se debe poder extraer variables del target en un matching")
  void instanceOfWithAComplexPredicate() {

    /*
    Si bien no es posible capturar variables adicionales a `mri` como binding variable
    sí es posible acceder a la estructura del único binding variable capturado
    de esa forma se pueden extraer más variables y se pueden operar en la expresión de matching,
    es decir, hacen parte del `predicate`
     */

    var mr1 = new MyRecord("a","b",0);
    if(mr1 instanceof MyRecord mri && mri.c() > 0){
      assertTrue(false);
    }else{
      assertTrue(true);
    }
  }


}
