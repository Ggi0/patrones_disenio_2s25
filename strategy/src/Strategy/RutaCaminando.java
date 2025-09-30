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
 * 
 *  * PATRÓN STRATEGY - ESTRATEGIA CONCRETA #3: RUTA CAMINANDO
 * 
 * Esta clase implementa la interfaz EstrategiaRuta de forma ESPECÍFICA
 * para calcular rutas A PIE (caminando).
 * 
 * CARACTERÍSTICAS DE ESTA ESTRATEGIA:
 * - Velocidad promedio: 5 km/h (la más lenta)
 * - Vías permitidas: CARRETERAS, CICLOVÍAS y SENDEROS
 * - No puede usar: AUTOPISTAS (prohibido para peatones)
 * 
 * 
 *
 * @author gio
 */
public class RutaCaminando implements EstrategiaRuta {
    
    // 5 km/h es la velocidad promedio de una persona caminando
    private static final double VELOCIDAD_CAMINANDO = 5.0; // km/h
    
    // Constante para identificar el tipo de transporte
    private static final String TIPO_TRANSPORTE = "CAMINANDO";
    
    @Override
    public ResultadoRuta calcularRuta(Grafo grafo, Nodo origen, Nodo destino) {
        
        // Mensaje informativo
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("   CALCULANDO RUTA CAMINANDO");
        System.out.println("   De: " + origen.getNombre() + " → Hasta: " + destino.getNombre());
        System.out.println("   Velocidad promedio: " + VELOCIDAD_CAMINANDO + " km/h");
        System.out.println("═══════════════════════════════════════\n");
        
        // Delega el cálculo con parámetros específicos de peatón
        return CalculadorRutas.calcularRutaOptima(
            grafo,
            origen,
            destino,
            TIPO_TRANSPORTE,        // "CAMINANDO" - filtra vías apropiadas
            VELOCIDAD_CAMINANDO     // 5 km/h
        );
    }
    
    @Override
    public String obtenerTipoTransporte() {
        return "A pie";
    }
    
    @Override
    public double obtenerVelocidadPromedio() {
        return VELOCIDAD_CAMINANDO;
    }
    
    
}
