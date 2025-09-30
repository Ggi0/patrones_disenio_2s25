/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejemplo_3;

/**
 *
 * @author Yair Lopez
 */
public class DemoMultiThread {
    public static void main(String[] args) {
        System.out.println("si se muestra el mismo valor, entonces el singleton fue reutilizado (Bien)");
        System.out.println("si se muestra un valor distino, entonces el singleton fue creado 2 veces  (mal)");
        System.out.println("resultado");
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("FOO");
            System.out.println(singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");
            System.out.println(singleton.value);
        }
    }
}