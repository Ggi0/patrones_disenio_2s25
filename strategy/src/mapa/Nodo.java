/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

/**
 *
 * @author gio
 */
public class Nodo {
    
    // ATRIBUTOS
    
    private String nombre;
    private int x;  // coordenada x para dibujar en el mapa
    private int y;  // coordenada y para dibujar en el panel

    
    // constructor 
    public Nodo(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
    /**
     * Método para comparar nodos por nombre
     * Útil para buscar nodos en listas
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nodo nodo = (Nodo) obj;
        return nombre.equals(nodo.nombre);
    }
    
    @Override
    public int hashCode() {
        return nombre.hashCode();
    }
    
    @Override
    public String toString() {
        return "Nodo{" + nombre + " (" + x + "," + y + ")}";
    }
    
    
    
    
    
}
