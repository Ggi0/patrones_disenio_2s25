# Explicación del Singleton Multi-Thread - Línea por Línea

## 📋 Índice
1. [DemoMultiThread.java](#demomultithreadjava)
2. [Singleton.java](#singletonjava)
3. [Problema de Concurrencia](#problema-de-concurrencia)
4. [Diferencias con DemoSingleton](#diferencias-con-demosingleton)

---

## DemoMultiThread.java

### Línea 6
```java
package Ejemplo_2;
```
**Explicación:** Define el paquete `Ejemplo_2`. Este es diferente al `Ejemplo_1` anterior, lo que nos permite tener dos implementaciones separadas para comparar.

---

### Línea 11
```java
public class DemoMultiThread {
```
**Explicación:** Declaramos la clase `DemoMultiThread`. Esta clase demostrará el problema de concurrencia del patrón Singleton cuando múltiples threads intentan acceder simultáneamente.

---

### Línea 12
```java
public static void main(String[] args) {
```
**Explicación:** Método `main`, el punto de entrada del programa.

---

### Líneas 13-15
```java
System.out.println("si se muestra el mismo valor, entonces el singleton fue reutilizado (Bien)");
System.out.println("si se muestra un valor distino, entonces el singleton fue creado 2 veces  (mal)");
System.out.println("resultado");
```
**Explicación:** Mensajes informativos que explican qué esperar:
- Mismo valor = Singleton funciona correctamente ✓
- Valores diferentes = Se crearon múltiples instancias (fallo del patrón) ✗

---

### Línea 16
```java
Thread threadFoo = new Thread(new ThreadFoo());
```
**Explicación:** Crea el primer thread llamado `threadFoo`:
- `new Thread()` → Crea un nuevo hilo de ejecución
- `new ThreadFoo()` → Le pasa una instancia de la clase interna `ThreadFoo` que implementa `Runnable`
- Este thread intentará obtener la instancia del Singleton con el valor `"FOO"`

---

### Línea 17
```java
Thread threadBar = new Thread(new ThreadBar());
```
**Explicación:** Crea el segundo thread llamado `threadBar`:
- Similar al anterior, pero usa la clase `ThreadBar`
- Este thread intentará obtener la instancia del Singleton con el valor `"BAR"`
- **Ambos threads se ejecutarán simultáneamente**, creando una condición de carrera (race condition)

---

### Línea 18
```java
threadFoo.start();
```
**Explicación:** Inicia la ejecución del primer thread:
- `start()` → Comienza la ejecución paralela del thread
- El método `run()` de `ThreadFoo` se ejecutará en un hilo separado
- **NO espera** a que termine para continuar

---

### Línea 19
```java
threadBar.start();
```
**Explicación:** Inicia la ejecución del segundo thread:
- Se ejecuta **casi simultáneamente** con `threadFoo`
- Ambos threads ahora están compitiendo por crear la instancia del Singleton
- Esta es la clave para demostrar el problema de concurrencia

---

### Línea 21
```java
static class ThreadFoo implements Runnable {
```
**Explicación:** Define una clase interna estática que implementa la interfaz `Runnable`:
- `static class` → Clase anidada estática (no necesita instancia de la clase externa)
- `implements Runnable` → Debe implementar el método `run()`
- Esta clase representa la tarea que ejecutará el primer thread

---

### Línea 22
```java
@Override
```
**Explicación:** Anotación que indica que estamos sobrescribiendo un método de la interfaz `Runnable`.

---

### Línea 23
```java
public void run() {
```
**Explicación:** Método obligatorio de la interfaz `Runnable`. Contiene el código que se ejecutará cuando el thread inicie.

---

### Línea 24
```java
Singleton singleton = Singleton.getInstance("FOO");
```
**Explicación:** El thread intenta obtener la instancia del Singleton con el valor `"FOO"`:
- Si llega primero → Creará la instancia con `"FOO"`
- Si llega después → Debería obtener la instancia existente
- **Problema:** Sin sincronización, ambos threads pueden crear su propia instancia

---

### Línea 25
```java
System.out.println(singleton.value);
```
**Explicación:** Imprime el valor de la instancia obtenida. Esto nos permitirá ver si ambos threads obtuvieron la misma instancia o no.

---

### Línea 29
```java
static class ThreadBar implements Runnable {
```
**Explicación:** Define la segunda clase interna estática, similar a `ThreadFoo` pero para el segundo thread.

---

### Línea 30
```java
@Override
```
**Explicación:** Indica que sobrescribimos el método `run()`.

---

### Línea 31
```java
public void run() {
```
**Explicación:** Método que contiene el código del segundo thread.

---

### Línea 32
```java
Singleton singleton = Singleton.getInstance("BAR");
```
**Explicación:** El segundo thread intenta obtener la instancia del Singleton con el valor `"BAR"`:
- Compite con `threadFoo` por crear/obtener la instancia
- La condición de carrera (race condition) ocurre aquí

---

### Línea 33
```java
System.out.println(singleton.value);
```
**Explicación:** Imprime el valor obtenido por el segundo thread.

---

## Singleton.java

### Línea 6
```java
package Ejemplo_2;
```
**Explicación:** Define el paquete `Ejemplo_2`, debe coincidir con el de `DemoMultiThread`.

---

### Línea 11
```java
public final class Singleton {
```
**Explicación:** Declaramos la clase como `final` para evitar herencia que pueda romper el patrón.

---

### Línea 12
```java
private static Singleton instance;
```
**Explicación:** Variable estática privada que almacena la única instancia:
- `private` → No accesible desde fuera
- `static` → Compartida por toda la clase
- **Importante:** No está sincronizada, lo que causa problemas con múltiples threads

---

### Línea 13
```java
public String value;
```
**Explicación:** Atributo público que almacena el valor para demostrar qué instancia se creó.

---

### Línea 16
```java
private Singleton(String value){
```
**Explicación:** Constructor privado que impide la creación directa de instancias con `new`.

---

### Líneas 17-19
```java
try{Thread.sleep(1000);
}catch (InterruptedException ex){
    ex.printStackTrace();
}
```
**Explicación:** Simula un retraso de 1 segundo durante la construcción:
- `Thread.sleep(1000)` → Pausa el thread por 1000 milisegundos
- Este retraso **amplifica el problema de concurrencia**
- Mientras un thread está "durmiendo" aquí, el otro puede entrar y también crear una instancia

---

### Línea 21
```java
this.value = value;
```
**Explicación:** Asigna el valor recibido al atributo de la instancia.

---

### Línea 25
```java
public static Singleton getInstance(String value){
```
**Explicación:** Método público estático para obtener la instancia:
- **NO está sincronizado** → Múltiples threads pueden ejecutarlo simultáneamente
- Esta es la causa del problema de concurrencia

---

### Línea 26
```java
if (instance == null) {
```
**Explicación:** Verifica si la instancia existe:
- **Problema crítico:** Ambos threads pueden evaluar esto como `true` simultáneamente
- Si ambos entran al `if`, ambos crearán una instancia

---

### Línea 27
```java
instance = new Singleton(value);
```
**Explicación:** Crea la instancia si no existe:
- Con el `sleep(1000)`, hay tiempo suficiente para que ambos threads entren aquí
- Resultado: Se crean dos instancias, rompiendo el patrón Singleton

---

### Línea 29
```java
return instance;
```
**Explicación:** Retorna la instancia (puede ser diferente para cada thread si hubo race condition).

---

## Problema de Concurrencia

### 🔴 ¿Qué sucede en la ejecución?

#### Escenario con Race Condition:

```
Tiempo    ThreadFoo                           ThreadBar
------    ---------                           ---------
t=0       start()                             start()
t=1       getInstance("FOO")                  getInstance("BAR")
t=2       if (instance == null) → true        if (instance == null) → true
t=3       new Singleton("FOO")                new Singleton("BAR")
t=4       Thread.sleep(1000) inicia           Thread.sleep(1000) inicia
...       (durmiendo)                         (durmiendo)
t=1004    this.value = "FOO"                  this.value = "BAR"
t=1005    return instance                     return instance
```

**Resultado:** 
```
FOO
BAR
```
o
```
BAR
FOO
```

**¡Dos instancias diferentes! El patrón Singleton ha fallado.**

---

### ✅ Resultado Ideal (con sincronización):

```
FOO
FOO
```
o
```
BAR
BAR
```

---

## Diferencias con DemoSingleton

| Característica | DemoSingleton | DemoMultiThread |
|----------------|---------------|-----------------|
| **Ejecución** | Secuencial (una línea después de otra) | Paralela (dos threads simultáneos) |
| **Llamadas a getInstance()** | Una después de la otra | Casi simultáneas |
| **Problema demostrado** | Funcionamiento básico del patrón | Problema de concurrencia (thread-safety) |
| **Resultado esperado** | Siempre `FOO FOO` | Puede ser `FOO BAR` (error) |
| **¿Falla el Singleton?** | No, funciona correctamente | Sí, se crean múltiples instancias |

---

## 🔧 Soluciones al Problema

### 1. Sincronización del Método Completo
```java
public static synchronized Singleton getInstance(String value){
    if (instance == null) {
        instance = new Singleton(value);
    }
    return instance;
}
```
✅ **Ventaja:** Simple y seguro  
⚠️ **Desventaja:** Bloquea el método completo, afecta rendimiento

---

### 2. Double-Checked Locking
```java
public static Singleton getInstance(String value){
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton(value);
            }
        }
    }
    return instance;
}
```
✅ **Ventaja:** Mejor rendimiento  
⚠️ **Desventaja:** Más complejo

---

### 3. Eager Initialization (Inicialización Anticipada)
```java
private static final Singleton instance = new Singleton("default");

public static Singleton getInstance(String value){
    return instance;
}
```
✅ **Ventaja:** Thread-safe por naturaleza  
⚠️ **Desventaja:** Se crea aunque nunca se use

---

### 4. Bill Pugh Singleton (Holder Pattern)
```java
private Singleton(String value) {
    this.value = value;
}

private static class SingletonHolder {
    private static final Singleton INSTANCE = new Singleton("default");
}

public static Singleton getInstance(String value) {
    return SingletonHolder.INSTANCE;
}
```
✅ **Ventaja:** Lazy loading + Thread-safe sin sincronización  
✅ **Mejor práctica** para la mayoría de casos

---

### 5. Enum Singleton (Recomendado por Joshua Bloch)
```java
public enum Singleton {
    INSTANCE;
    public String value;
}

// Uso:
Singleton.INSTANCE.value = "FOO";
```
✅ **Ventaja:** Thread-safe, protección contra serialización y reflexión  
✅ **Más seguro y simple**

---

## 📝 Resumen

**DemoMultiThread** demuestra que el patrón Singleton sin sincronización **NO es thread-safe**. Cuando múltiples threads acceden simultáneamente a `getInstance()`, pueden crear múltiples instancias, violando el principio fundamental del patrón Singleton: **una única instancia**.

El `Thread.sleep(1000)` amplifica este problema al darles tiempo suficiente a ambos threads para entrar en la sección crítica simultáneamente.