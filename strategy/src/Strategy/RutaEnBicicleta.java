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
 * 
 * PATRÓN STRATEGY - ESTRATEGIA CONCRETA #2: RUTA EN BICICLETA
 * 
 * 
 * Esta clase implementa la interfaz EstrategiaRuta de forma ESPECÍFICA
 * para calcular rutas en BICICLETA.
 * 
 * CARACTERÍSTICAS DE ESTA ESTRATEGIA:
 * - Velocidad promedio: 15 km/h (velocidad media)
 * - Vías permitidas: CARRETERAS, CICLOVÍAS y SENDEROS
 * - No puede usar: AUTOPISTAS (demasiado peligrosas/prohibidas)
 * 
 * 
 *
 * @author gio
 */
public class RutaEnBicicleta implements EstrategiaRuta {
    
    
    // 15 km/h es una velocidad realista para ciclismo urbano casual
    private static final double VELOCIDAD_BICICLETA = 15.0; // km/h
    
    // Constante para identificar el tipo de transporte
    private static final String TIPO_TRANSPORTE = "BICICLETA";
    
    
    
    @Override
    public ResultadoRuta calcularRuta(Grafo grafo, Nodo origen, Nodo destino) {
        
        // Mensaje informativo
        System.out.println("\n===================================================");
        System.out.println("   CALCULANDO RUTA EN BICICLETA");
        System.out.println("   De: " + origen.getNombre() + " -> Hasta: " + destino.getNombre());
        System.out.println("   Velocidad promedio: " + VELOCIDAD_BICICLETA + " km/h");
        System.out.println("===================================================\n");
        
        // Delega el cálculo con parámetros específicos de bicicleta
        return CalculadorRutas.calcularRutaOptima(
            grafo,
            origen,
            destino,
            TIPO_TRANSPORTE,        // "BICICLETA" - filtra vías apropiadas
            VELOCIDAD_BICICLETA     // 15 km/h
        );
    }
    
    @Override
    public String obtenerTipoTransporte() {
        return "Bicicleta";
    }
    
    @Override
    public double obtenerVelocidadPromedio() {
        return VELOCIDAD_BICICLETA;
    }
    
    
}
