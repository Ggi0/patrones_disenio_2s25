
package mapa;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 *      Representa el mapa completo como un grafo
 *      Contiene todos los nodos (ubicaciones) y aristas (caminos)
 * 
 *
 * @author gio
 */
public class Grafo {
    
    // ATRIBUTOS
    
    private List<Nodo> nodos;       // Lista de todos los nodos del mapa
    private List<Arista> aristas;   // Lista de todas las conexiones

    
    // contructor
    public Grafo() {
        this.nodos = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }
    
    
    /**
     * Agrega un nodo al grafo (mapa)
     * @param nodo Nodo a agregar
     */
    public void agregarNodo(Nodo nodo) {
        if (!nodos.contains(nodo)) {
            nodos.add(nodo);
            System.out.println("Nodo agregado: " + nodo);
        }
    }
    
    
        
    /**
     * Agrega una arista bidireccional entre dos nodos
     * @param origen Nodo inicial
     * @param destino Nodo final
     * @param distancia Distancia en km
     * @param tipoVia Tipo de vía
     */
    public void agregarArista(Nodo origen, Nodo destino, double distancia, TipoVia tipoVia) {
        // Verifica que ambos nodos existan en el grafo
        if (!nodos.contains(origen)) {
            System.out.println("Error: Nodo origen no existe");
            return;
        }
        if (!nodos.contains(destino)) {
            System.out.println("Error: Nodo destino no existe");
            return;
        }
        
        //     public Arista(Nodo origen, Nodo destino, double distancia, TipoVia tipoVia) {
        // Crea la arista
        Arista arista = new Arista(origen, destino, distancia, tipoVia);
        aristas.add(arista);
        System.out.println("Arista agregada: " + arista);
    }
    
    
    
     /**
     * Obtiene un nodo por su nombre
     * @param nombre Nombre del nodo a buscar
     * @return El nodo encontrado o null
     */
    public Nodo obtenerNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getNombre().equalsIgnoreCase(nombre)) {
                return nodo;
            }
        }
        return null;
    }

    
    
    /**
     * Obtiene todas las aristas conectadas a un nodo específico
     * @param nodo Nodo del cual obtener las conexiones
     * @return Lista de aristas conectadas
     */
    public List<Arista> obtenerAristasDeNodo(Nodo nodo) {
        List<Arista> aristasDelNodo = new ArrayList<>();
        for (Arista arista : aristas) {
            if (arista.contieneNodo(nodo)) {
                aristasDelNodo.add(arista);
            }
        }
        return aristasDelNodo;
    }
    
    /**
     * Obtiene los nodos vecinos de un nodo dado
     * @param nodo Nodo del cual obtener vecinos
     * @return Lista de nodos vecinos
     */
    public List<Nodo> obtenerVecinos(Nodo nodo) {
        List<Nodo> vecinos = new ArrayList<>();
        List<Arista> aristasDelNodo = obtenerAristasDeNodo(nodo);
        
        for (Arista arista : aristasDelNodo) {
            Nodo vecino = arista.getNodoOpuesto(nodo);
            if (vecino != null) {
                vecinos.add(vecino);
            }
        }
        
        return vecinos;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }
    
    
    
    /**
     * Imprime toda la información del grafo
     */
    public void imprimirGrafo() {
        System.out.println("\n=== INFORMACIÓN DEL GRAFO ===");
        System.out.println("Nodos: " + nodos.size());
        for (Nodo nodo : nodos) {
            System.out.println("  " + nodo);
        }
        
        System.out.println("\nAristas: " + aristas.size());
        for (Arista arista : aristas) {
            System.out.println("  " + arista);
        }
        System.out.println("=============================\n");
    }
      
    
    
}
