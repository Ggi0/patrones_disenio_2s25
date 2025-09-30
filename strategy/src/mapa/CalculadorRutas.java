/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;
import java.util.*;


/**
 *
 * Clase que calcula rutas en el grafo usando el algoritmo de Dijkstra
 * Considera el tipo de transporte para filtrar vías transitables
 * 
 * 
 * @author gio
 */
public class CalculadorRutas {
    
    
    /**
     * Clase interna para representar el resultado de una ruta
     */
    public static class ResultadoRuta {
        private List<Nodo> camino;          // Lista ordenada de nodos
        private double distanciaTotal;      // Distancia en km
        private double tiempoTotal;         // Tiempo en minutos
        private String tipoTransporte;      // Tipo de transporte usado
        
        
        // constructor
        public ResultadoRuta(   List<Nodo> camino, 
                                double distanciaTotal, 
                                double tiempoTotal, 
                                String tipoTransporte ) 
        {
            this.camino = camino;
            this.distanciaTotal = distanciaTotal;
            this.tiempoTotal = tiempoTotal;
            this.tipoTransporte = tipoTransporte;
        }
        
        
        // getters
        
        public List<Nodo> getCamino() { return camino; }
        public double getDistanciaTotal() { return distanciaTotal; }
        public double getTiempoTotal() { return tiempoTotal; }
        public String getTipoTransporte() { return tipoTransporte; }
        
        
        public boolean existeRuta() {
            return camino != null && !camino.isEmpty();
        }
        
        @Override
        public String toString() {
            
            if (!existeRuta()) {
                return "No existe ruta disponible para " + tipoTransporte;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("=== RUTA ENCONTRADA ===\n");
            sb.append("Transporte: ").append(tipoTransporte).append("\n");
            sb.append("Distancia: ").append(String.format("%.2f km", distanciaTotal)).append("\n");
            sb.append("Tiempo estimado: ").append(String.format("%.1f minutos", tiempoTotal)).append("\n");
            sb.append("Camino: ");
            
            for (int i = 0; i < camino.size(); i++) {
                sb.append(camino.get(i).getNombre());
                if (i < camino.size() - 1) {
                    sb.append(" → ");
                }
            }
            
            return sb.toString();
        }
    }
    
    
    /**
     * Calcula la ruta más corta usando Dijkstra
     * Filtra las aristas según el tipo de transporte
     * 
     * @param grafo Grafo con el mapa
     * @param origen Nodo inicial
     * @param destino Nodo final
     * @param tipoTransporte Tipo de transporte (CARRO, BICICLETA, CAMINANDO)
     * @param velocidadPromedio Velocidad en km/h
     * @return Resultado con la ruta óptima
     */
    public static ResultadoRuta calcularRutaOptima(Grafo grafo, Nodo origen, 
                                                   Nodo destino, String tipoTransporte, 
                                                   double velocidadPromedio) {
        
        // Validaciones
        if (origen == null || destino == null) {
            return new ResultadoRuta(null, 0, 0, tipoTransporte);
        }
        
        if (origen.equals(destino)) {
            List<Nodo> caminoDirecto = Arrays.asList(origen);
            return new ResultadoRuta(caminoDirecto, 0, 0, tipoTransporte);
        }
        
        // Estructuras para Dijkstra
        Map<Nodo, Double> distancias = new HashMap<>();
        Map<Nodo, Nodo> predecesores = new HashMap<>();
        Set<Nodo> visitados = new HashSet<>();
        PriorityQueue<NodoDistancia> cola = new PriorityQueue<>();
        
        // Inicializar distancias
        for (Nodo nodo : grafo.getNodos()) {
            distancias.put(nodo, Double.MAX_VALUE);
        }
        distancias.put(origen, 0.0);
        cola.add(new NodoDistancia(origen, 0.0));
        
        // Algoritmo de Dijkstra
        while (!cola.isEmpty()) {
            NodoDistancia actual = cola.poll();
            Nodo nodoActual = actual.nodo;
            
            if (visitados.contains(nodoActual)) {
                continue;
            }
            
            visitados.add(nodoActual);
            
            // Si llegamos al destino, terminamos
            if (nodoActual.equals(destino)) {
                break;
            }
            
            // Explorar vecinos
            List<Arista> aristasVecinas = grafo.obtenerAristasDeNodo(nodoActual);
            
            for (Arista arista : aristasVecinas) {
                // FILTRO IMPORTANTE: Solo considerar aristas transitables
                if (!arista.getTipoVia().esTransitable(tipoTransporte)) {
                    continue;  // Saltar esta arista si no es transitable
                }
                
                Nodo vecino = arista.getNodoOpuesto(nodoActual);
                
                if (visitados.contains(vecino)) {
                    continue;
                }
                
                double nuevaDistancia = distancias.get(nodoActual) + arista.getDistancia();
                
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, nodoActual);
                    cola.add(new NodoDistancia(vecino, nuevaDistancia));
                }
            }
        }
        
        // Reconstruir el camino
        List<Nodo> camino = reconstruirCamino(predecesores, origen, destino);
        
        if (camino == null || camino.isEmpty()) {
            return new ResultadoRuta(null, 0, 0, tipoTransporte);
        }
        
        double distanciaTotal = distancias.get(destino);
        double tiempoTotal = (distanciaTotal / velocidadPromedio) * 60; // Convertir a minutos
        
        return new ResultadoRuta(camino, distanciaTotal, tiempoTotal, tipoTransporte);
    }
    
    
    /**
     * Reconstruye el camino desde el origen hasta el destino
     */
    private static List<Nodo> reconstruirCamino(Map<Nodo, Nodo> predecesores, 
                                                Nodo origen, Nodo destino) {
        
        if (!predecesores.containsKey(destino)) {
            return null; // No hay camino
        }
        
        LinkedList<Nodo> camino = new LinkedList<>();
        Nodo actual = destino;
        
        while (actual != null) {
            camino.addFirst(actual);
            actual = predecesores.get(actual);
            
            // Evitar bucles infinitos
            if (camino.size() > predecesores.size() + 1) {
                return null;
            }
        }
        
        return camino;
    }
    
    /**
     * Clase auxiliar para la cola de prioridad de Dijkstra
     */
    private static class NodoDistancia implements Comparable<NodoDistancia> {
        Nodo nodo;
        double distancia;
        
        NodoDistancia(Nodo nodo, double distancia) {
            this.nodo = nodo;
            this.distancia = distancia;
        }
        
        @Override
        public int compareTo(NodoDistancia otro) {
            return Double.compare(this.distancia, otro.distancia);
        }
    }
    
    
    
}
