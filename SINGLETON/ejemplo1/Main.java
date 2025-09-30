package ejemplo1;
public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance("Hola");
        Singleton s2 = Singleton.getInstance("Mundo");

        System.out.println(s1.value); // Imprime "Hola"
        System.out.println(s2.value); // También "Hola"
        System.out.println(s1 == s2); // true (son el mismo objeto)
    }
}
