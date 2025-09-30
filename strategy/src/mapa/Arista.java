/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

/**
 * 
 * Representa una conexión/camino entre dos nodos
 * Contiene la distancia y el tipo de vía
 *
 * @author gio
 */
public class Arista {
    
    // ATRIBUTOS
    private Nodo origen;        // Nodo de inicio
    private Nodo destino;       // Nodo de fin
    private double distancia;   // Distancia en kilómetros
    private TipoVia tipoVia;    // Tipo de camino (carretera, ciclovía, etc.)

    
    // construcctor
    public Arista(Nodo origen, Nodo destino, double distancia, TipoVia tipoVia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
        this.tipoVia = tipoVia;
    }
    
    // getters y setters

    public Nodo getOrigen() {
        return origen;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public TipoVia getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(TipoVia tipoVia) {
        this.tipoVia = tipoVia;
    }
    
    
    
    /**
     * Verifica si un nodo dado es parte de esta arista
     * @param nodo Nodo a verificar
     * @return true si el nodo es origen o destino
     */
    public boolean contieneNodo(Nodo nodo) {
        return origen.equals(nodo) || destino.equals(nodo);
    }
    
    /**
     * Obtiene el nodo opuesto dado uno de los nodos
     * Útil para navegar por el grafo
     * @param nodo Nodo actual
     * @return El otro nodo de la arista
     */
    public Nodo getNodoOpuesto(Nodo nodo) {
        if (origen.equals(nodo)) {
            return destino;
        } else if (destino.equals(nodo)) {
            return origen;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Arista{" + origen.getNombre() + " <-> " + destino.getNombre() + 
               ", " + distancia + "km, " + tipoVia + "}";
    }
    
    
    
    
    
}
