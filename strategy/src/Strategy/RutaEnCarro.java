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
 * @author gio
 */
public class RutaEnCarro implements EstrategiaRuta {
    
    private static final double VELOCIDAD_CARRO = 60.0;
    
    private static final String TIPO_TRANSPORTE = "CARRO";
    
    
    @Override
    public ResultadoRuta calcularRuta(Grafo grafo, Nodo origen, Nodo destino) {
        
        // Mensaje de logging para seguimiento
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("   CALCULANDO RUTA EN AUTOMÓVIL");
        System.out.println("   De: " + origen.getNombre() + " -> Hasta: " + destino.getNombre());
        System.out.println("   Velocidad promedio: " + VELOCIDAD_CARRO + " km/h");
        System.out.println("═══════════════════════════════════════\n");
        
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
