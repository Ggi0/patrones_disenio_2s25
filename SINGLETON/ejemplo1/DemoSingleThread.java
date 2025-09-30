package ejemplo1;
public class DemoSingleThread {
    public static void main(String[] args) {
        System.out.println("""
            Si ves el mismo valor, entonces el singleton fue reutilizado (¡bien!)
            Si ves valores diferentes, entonces se crearon 2 singletons (¡mal!).

            RESULTADO:
            """);
        Singleton singleton = Singleton.getInstance("Singleton Reutilizado");
        Singleton anotherSingleton = Singleton.getInstance("Singleton se creo dos veces");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);
    }
}