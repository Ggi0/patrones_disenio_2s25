/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy;

import mapa.CalculadorRutas.ResultadoRuta;
import mapa.Grafo;
import mapa.Nodo;

/**
 *
 * 
 *  TODAS LAS ESTRATEGIAS DEBEN CUMPLIR LO ESTABLECIDO AQUI ---> INTERFAZ ESTRATEGICA
 * 
 * 
 *  * PROPÓSITO:
 * - Estandarizar el método que todas las estrategias deben implementar
 * - Permitir al Contexto trabajar con cualquier estrategia sin conocer 
 *   los detalles de implementación
 * - Facilitar agregar nuevas estrategias sin modificar código existente
 * 
 * 
 * @author gio
 */
public interface EstrategiaRuta {
    
    /**
     * Método principal que todas las estrategias DEBEN implementar
     * 
     * Este método calcula la ruta óptima entre dos nodos según el tipo
     * de transporte específico de cada estrategia concreta.
     * 
     * @param grafo El mapa completo con todos los nodos y aristas
     * @param origen Punto de partida del viaje
     * @param destino Punto final del viaje
     * @return ResultadoRuta con el camino, distancia y tiempo calculados
     */
    ResultadoRuta calcularRuta(Grafo grafo, Nodo origen, Nodo destino);
    
    
    /**
     * Método auxiliar para obtener el nombre del tipo de transporte
     * 
     * Útil para mostrar información al usuario y para logging
     * 
     * @return String con el nombre del transporte (ej: "Automóvil", "Bicicleta")
     */
    String obtenerTipoTransporte();
    
    
    
    /**
     * Método auxiliar para obtener la velocidad promedio en km/h
     * 
     * Útil para cálculos de tiempo y comparaciones
     * 
     * @return Velocidad promedio en km/h
     */
    double obtenerVelocidadPromedio();
    
}
