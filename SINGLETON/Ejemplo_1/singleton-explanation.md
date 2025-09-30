# Explicación del Patrón Singleton - Línea por Línea

## 📋 Índice
1. [DemoSingleton.java](#demosingletonjava)
2. [Singleton.java](#singletonjava)
3. [Conclusión](#conclusión)

---

## DemoSingleton.java

### Línea 1
```java
package Ejemplo_1;
```
**Explicación:** Define el paquete donde está ubicada nuestra clase. Esto ayuda a organizar el código en diferentes módulos.

---

### Línea 5
```java
public class DemoSingleton {
```
**Explicación:** Declaramos una clase pública llamada `DemoSingleton`. Esta será nuestra clase de prueba para demostrar cómo funciona el patrón Singleton.

---

### Línea 6
```java
public static void main(String[] args) {
```
**Explicación:** El método `main` es el punto de entrada de nuestra aplicación Java. Aquí es donde comienza la ejecución del programa.

---

### Líneas 7-9
```java
System.out.println("si se muestra el mismo valor, entonces el singleton fue reutilizado (Bien)");
System.out.println("si se muestra un valor distino, entonces el singleton fue creado 2 veces  (mal)");
System.out.println("resultado");
```
**Explicación:** Estos tres `System.out.println()` imprimen mensajes informativos en la consola:
- Le explican al usuario qué esperar
- Si ve el mismo valor = el Singleton funciona correctamente ✓
- Si ve valores diferentes = se crearon múltiples instancias (error) ✗

---

### Línea 11
```java
Singleton s1 = Singleton.getInstance("FOO");
```
**Explicación:** Intentamos obtener la primera instancia del Singleton usando el método estático `getInstance()`, pasándole el valor `"FOO"`. Como es la primera vez que se llama, se creará una nueva instancia con este valor.

---

### Línea 12
```java
Singleton s2 = Singleton.getInstance("BAR");
```
**Explicación:** Intentamos obtener una segunda instancia del Singleton, ahora con el valor `"BAR"`. Si el patrón funciona correctamente, NO se creará una nueva instancia, sino que se devolverá la misma instancia creada anteriormente (con valor `"FOO"`).

---

### Líneas 14-15
```java
System.out.println(s1.value);
System.out.println(s2.value);
```
**Explicación:** Imprimimos el valor de ambas referencias:
- Si el Singleton funciona bien → ambas mostrarán `"FOO"`
- Si hay un error → podrían mostrar valores diferentes

---

## Singleton.java

### Línea 1
```java
package Ejemplo_1;
```
**Explicación:** Define el paquete. Debe ser el mismo que `DemoSingleton` para que puedan comunicarse.

---

### Línea 3
```java
public final class Singleton{
```
**Explicación:** Declaramos la clase como `final`. Esto evita que otras clases puedan heredar de `Singleton`, lo cual podría romper el patrón al crear subclases con múltiples instancias.

---

### Línea 4
```java
private static Singleton instance;
```
**Explicación:** Esta es la variable clave del patrón Singleton:
- `private` → Nadie puede acceder directamente desde fuera de la clase
- `static` → Pertenece a la clase, no a instancias individuales (compartida por todos)
- Almacena la única instancia que existirá de esta clase

---

### Línea 5
```java
public String value;
```
**Explicación:** Un atributo público que almacena un valor tipo `String`. En este caso, se usa para demostrar qué valor tiene cada instancia (para verificar si es la misma o no).

---

### Línea 7
```java
private Singleton(String value){
```
**Explicación:** El constructor es `private`. Esto es **fundamental** en el patrón Singleton porque:
- Impide que alguien cree instancias con `new Singleton()` desde fuera
- La única forma de obtener una instancia es a través del método `getInstance()`

---

### Líneas 8-9
```java
try { Thread.sleep(1000); }
catch (InterruptedException ex) {ex.printStackTrace();}
```
**Explicación:** Simula un retraso de 1 segundo (1000 milisegundos) durante la construcción del objeto. Esto es para:
- Demostrar problemas de concurrencia en ambientes multi-thread
- Si dos threads llaman a `getInstance()` al mismo tiempo, ambos podrían pasar la validación `if (instance == null)` antes de que se complete la creación

---

### Línea 10
```java
this.value = value;
```
**Explicación:** Asigna el valor recibido como parámetro al atributo `value` de la instancia que se está creando.

---

### Línea 14
```java
public static Singleton getInstance(String value){
```
**Explicación:** Este es el método público y estático que permite obtener la única instancia de la clase:
- `public` → Accesible desde cualquier lugar
- `static` → Se puede llamar sin necesidad de tener una instancia (`Singleton.getInstance()`)
- Es la única forma de obtener la instancia del Singleton

---

### Línea 15
```java
if (instance == null){
```
**Explicación:** Verifica si la instancia todavía no ha sido creada. Esta es la lógica de "lazy initialization" (inicialización perezosa):
- Solo crea la instancia cuando se solicita por primera vez
- Si ya existe, salta la creación

---

### Línea 16
```java
instance = new Singleton (value);
```
**Explicación:** Si la instancia es `null`, la creamos por primera y única vez usando el constructor privado, pasándole el valor recibido.

---

### Línea 18
```java
return instance;
```
**Explicación:** Retorna la instancia del Singleton:
- Si ya existía → devuelve la misma instancia
- Si no existía → devuelve la instancia recién creada

---

## Conclusión

### ✅ Resultado Esperado
Cuando ejecutamos `DemoSingleton`, deberíamos ver:
```
FOO
FOO
```

**¿Por qué?** Porque la primera llamada a `getInstance("FOO")` crea la instancia con el valor `"FOO"`, y la segunda llamada a `getInstance("BAR")` simplemente retorna esa misma instancia, ignorando el nuevo valor `"BAR"`.

---

### ⚠️ Problema de Concurrencia

Este código tiene un **problema crítico**: **NO es thread-safe**.

**¿Qué significa esto?**
- Si múltiples threads llaman a `getInstance()` simultáneamente
- Ambos pueden pasar la validación `if (instance == null)` antes de que se complete la creación
- Se crearían múltiples instancias, rompiendo el patrón Singleton

**Por eso existe `DemoMultiThread`:** para demostrar este problema de concurrencia.

---

### 🔧 Soluciones Posibles

1. **Sincronización del método:**
   ```java
   public static synchronized Singleton getInstance(String value)
   ```

2. **Double-Checked Locking:**
   ```java
   if (instance == null) {
       synchronized (Singleton.class) {
           if (instance == null) {
               instance = new Singleton(value);
           }
       }
   }
   ```

3. **Inicialización estática (Eager Initialization):**
   ```java
   private static final Singleton instance = new Singleton("default");
   ```

4. **Enum Singleton (Recomendado por Joshua Bloch):**
   ```java
   public enum Singleton {
       INSTANCE;
       public String value;
   }
   ```