# Explicación del código Singleton en Java

Este código implementa el **patrón Singleton** en Java, que asegura que sólo exista **una única instancia** de la clase en toda la aplicación.

---

## Archivo: `Main.java`

```java
package practica.pkg2;
```
- Define que la clase pertenece al paquete `practica.pkg2`.  
- La carpeta del proyecto debe coincidir con este nombre para que no marque error.

```java
public class Main {
```
- Clase pública llamada `Main`.  
- Aquí estará el método principal que arranca la aplicación.

```java
    public static void main(String[] args) {
```
- Método de entrada en Java.  
- `public`: accesible desde cualquier parte.  
- `static`: no necesita un objeto para ejecutarse.  
- `void`: no devuelve nada.  
- `String[] args`: parámetros desde la línea de comandos.

```java
        Singleton s1 = Singleton.getInstance("Hola");
        Singleton s2 = Singleton.getInstance("Mundo");
```
- Llama al método estático `getInstance` de la clase `Singleton`.  
- La primera vez (`s1`), la instancia se crea con el valor `"Hola"`.  
- La segunda vez (`s2`), ya existe, así que devuelve la misma instancia (ignora `"Mundo"`).

```java
        System.out.println(s1.value); // Imprime "Hola"
        System.out.println(s2.value); // También "Hola"
        System.out.println(s1 == s2); // true (son el mismo objeto)
```
- Muestra el valor de `s1` (es `"Hola"`).  
- Muestra el valor de `s2` (también `"Hola"`, porque es la misma instancia).  
- Compara con `==` si `s1` y `s2` apuntan al mismo objeto en memoria → `true`.

```java
    }
}
```
- Cierra el método `main` y la clase `Main`.

---

## Archivo: `Singleton.java`

```java
package practica.pkg2;
```
- Pertenece al mismo paquete que `Main.java` (`practica.pkg2`), lo que permite que `Main` lo use sin importar extra.

```java
public final class Singleton {
```
- `public`: accesible desde cualquier clase.  
- `final`: no se puede heredar.  
- Clase `Singleton`.

```java
    private static Singleton instance;
```
- Variable estática que guarda la **única instancia** de la clase.  
- `private`: sólo accesible dentro de esta clase.

```java
    public String value;
```
- Atributo público para guardar un valor.  
- En este caso, sirve para mostrar qué cadena se usó al crear el Singleton.

```java
    private Singleton(String value) {
```
- Constructor **privado**: impide crear objetos con `new Singleton()`.  
- Solo puede ser llamado dentro de esta clase.

```java
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
```
- Simula que la inicialización tarda 1 segundo (`sleep(1000)`).  
- El `try/catch` captura la excepción si el hilo es interrumpido.

```java
        this.value = value;
    }
```
- Asigna el valor pasado al constructor al atributo de la clase.

```java
    public static Singleton getInstance(String value) {
```
- Método estático para obtener la instancia única.  
- Recibe un `value`, pero sólo será usado la **primera vez**.

```java
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }
}
```
- Si `instance` todavía es `null`, crea el objeto `Singleton`.  
- Si ya existe, simplemente devuelve la misma instancia.  
- Garantiza que solo se cree **una única vez**.

---

## Ejecución esperada

Cuando se ejecuta `Main`, la salida será:

```
Hola
Hola
true
```

---

## Resumen para la clase

- `Singleton` **controla la creación** de su única instancia.  
- El constructor es **privado** → no se puede crear con `new`.  
- `getInstance` devuelve siempre la **misma instancia**.  
- Aunque se intente crear con otro valor (`"Mundo"`), mantiene el primero (`"Hola"`).  
- Demuestra el principio: **“una única instancia global”**.
