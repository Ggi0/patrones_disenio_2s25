package Ejemplo_1;



public class DemoSingleton {
    public static void main(String[] args) {
        System.out.println("si se muestra el mismo valor, entonces el singleton fue reutilizado (Bien)");
        System.out.println("si se muestra un valor distino, entonces el singleton fue creado 2 veces  (mal)");
        System.out.println("resultado");

        Singleton s1 = Singleton.getInstance("FOO");
        Singleton s2 = Singleton.getInstance("BAR");

        System.out.println(s1.value);
        System.out.println(s2.value);
    }
}