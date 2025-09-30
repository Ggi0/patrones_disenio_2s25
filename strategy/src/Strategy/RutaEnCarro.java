/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy;

import mapa.CalculadorRutas;
import mapa.CalculadorRutas.ResultadoRuta;
import mapa.Grafo;
import mapa.Nodo;

/**
 * 
 * * PATRÓN STRATEGY - ESTRATEGIA CONCRETA #1: RUTA EN CARRO
 * 
 * Esta clase implementa la interfaz EstrategiaRuta de forma ESPECÍFICA
 * para calcular rutas en AUTOMÓVIL.
 * 
 * CARACTERÍSTICAS DE ESTA ESTRATEGIA:
 * - Velocidad promedio: 60 km/h (la más rápida)
 * - Vías permitidas: AUTOPISTAS y CARRETERAS únicamente
 * - No puede usar: CICLOVÍAS ni SENDEROS
 *
 * @author gio
 */
public class RutaEnCarro implements EstrategiaRuta {
    
    private static final double VELOCIDAD_CARRO = 60.0;
    
    private static final String TIPO_TRANSPORTE = "CARRO";
    
    
    
    
     /**
     * IMPLEMENTACIÓN DEL MÉTODO calcularRuta() PARA CARRO
     * 
     * Este método es la IMPLEMENTACIÓN ESPECÍFICA del algoritmo para carro.
     * 
     * PROCESO:
     * 1. Imprime mensaje informativo
     * 2. Llama al algoritmo Dijkstra con parámetros específicos de carro
     * 3. El algoritmo filtra automáticamente solo vías transitables en carro
     * 4. Retorna la ruta óptima encontrada
     * 
     * @param grafo Mapa completo con nodos y aristas
     * @param origen Nodo de partida
     * @param destino Nodo de llegada
     * @return ResultadoRuta con camino, distancia y tiempo
     */
    @Override
    public ResultadoRuta calcularRuta(Grafo grafo, Nodo origen, Nodo destino) {
        
        // Mensaje de logging para seguimiento
        System.out.println("\n=========================================");
        System.out.println("   CALCULANDO RUTA EN AUTOMÓVIL");
        System.out.println("   De: " + origen.getNombre() + " -> Hasta: " + destino.getNombre());
        System.out.println("   Velocidad promedio: " + VELOCIDAD_CARRO + " km/h");
        System.out.println("============================================\n");
        
        // DELEGACIÓN: Esta estrategia DELEGA el cálculo real al CalculadorRutas
        // del paquete mapa, pasándole los parámetros específicos del carro
        return CalculadorRutas.calcularRutaOptima(
            grafo,              // El mapa
            origen,             // Desde dónde
            destino,            // Hasta dónde
            TIPO_TRANSPORTE,    // "CARRO" - filtra solo vías transitables en carro
            VELOCIDAD_CARRO     // 60 km/h - para calcular tiempo
        );
    }
    
    
    @Override
    public String obtenerTipoTransporte() {
        return "Automóvil";
    }
    
    /**
     * Retorna la velocidad promedio para comparaciones
     */
    @Override
    public double obtenerVelocidadPromedio() {
        return VELOCIDAD_CARRO;
    }
    
    
}
