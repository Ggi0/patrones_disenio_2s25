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
 * PATRÓN STRATEGY - CONTEXTO
 * 
 * Esta clase es el CONTEXTO del patrón Strategy.
 * 
 * RESPONSABILIDADES DEL CONTEXTO:
 * 1. Mantener una referencia a la estrategia actual
 * 2. Permitir cambiar la estrategia en tiempo de ejecución
 * 3. Delegar el trabajo a la estrategia sin conocer su implementación
 * 4. Proporcionar una interfaz simple al cliente
 *
 * @author gio
 */
public class CalculadorDeRutas {
    
    // Referencia a la estrategia actual
    // Este es el NÚCLEO del patrón Strategy:
    // - Guardamos una referencia a la INTERFAZ, no a una clase concreta
    // - Esto permite cambiar de estrategia dinámicamente
    // - El contexto no sabe ni le importa qué estrategia concreta es
    private EstrategiaRuta estrategia;
    
    
    // Crea el contexto sin estrategia inicial.
    // La estrategia debe establecerse después con setEstrategia().
    public CalculadorDeRutas() { // constructor VACIO
        this.estrategia = null;
        System.out.println(" Contexto creado (sin estrategia inicial)");
    }
    
    // Crea el contexto con una estrategia ya definida.
    public CalculadorDeRutas(EstrategiaRuta estrategia) { // Constructor con una ESTRATEGIA
        this.estrategia = estrategia;
        System.out.println(" Contexto creado con estrategia: " + 
                          estrategia.obtenerTipoTransporte());
    }
    
    
    
    /**
     * MÉTODO CLAVE: CAMBIAR LA ESTRATEGIA EN TIEMPO DE EJECUCIÓN
     * 
     * Este método es FUNDAMENTAL en el patrón Strategy.
     * Permite cambiar el comportamiento del contexto dinámicamente.
     * 
     * @param estrategia La nueva estrategia a usar
     */
    public void setEstrategia(EstrategiaRuta estrategia) {
        
        // Validación: evitar estrategias null
        if (estrategia == null) {
            System.err.println("Error: No se puede establecer estrategia null");
            return;
        }
        
        // Asignar la nueva estrategia
        this.estrategia = estrategia;
        
        // Logging para seguimiento
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("ESTRATEGIA CAMBIADA");
        System.out.println("   Nueva estrategia: " + estrategia.obtenerTipoTransporte());
        System.out.println("   Velocidad: " + estrategia.obtenerVelocidadPromedio() + " km/h");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }
    
    public EstrategiaRuta getEstrategia() {
        return estrategia;
    }
    
    
    
    /**
     * ═══════════════════════════════════════════════════════════════════
     * MÉTODO PRINCIPAL: CALCULAR RUTA USANDO LA ESTRATEGIA
     * ═══════════════════════════════════════════════════════════════════
     * 
     * Este método es la INTERFAZ PÚBLICA del contexto.
     * 
     * FLUJO:
     * 1. Valida que haya una estrategia establecida
     * 2. Delega el cálculo a la estrategia actual
     * 3. La estrategia ejecuta su algoritmo específico
     * 4. Retorna el resultado
     * 
     * POLIMORFISMO EN ACCIÓN:
     * El contexto llama a estrategia.calcularRuta(), pero NO SABE
     * si está llamando a RutaEnCarro, RutaEnBicicleta o RutaCaminando.
     * Esto es POLIMORFISMO: un método, múltiples comportamientos.
     * 
     * 
     * @param grafo El mapa completo
     * @param origen Nodo de partida
     * @param destino Nodo de llegada
     * @return ResultadoRuta con el camino óptimo, o null si hay error
     */
    public ResultadoRuta calcularRutaOptima(Grafo grafo, Nodo origen, Nodo destino) {
        
        // ═══════════════════════════════════════════════════════════════
        // VALIDACIÓN: Verificar que hay una estrategia establecida
        // ═══════════════════════════════════════════════════════════════
        if (estrategia == null) {
            System.err.println("\n ERROR: No se ha establecido una estrategia");
            System.err.println("   Debe llamar a setEstrategia() primero");
            return null;
        }
        
        // ═══════════════════════════════════════════════════════════════
        // VALIDACIÓN: Verificar parámetros
        // ═══════════════════════════════════════════════════════════════
        if (grafo == null || origen == null || destino == null) {
            System.err.println("\n ERROR: Parámetros inválidos");
            return null;
        }
        
        // ═══════════════════════════════════════════════════════════════
        // DELEGACIÓN: Aquí ocurre la MAGIA del patrón Strategy
        // ═══════════════════════════════════════════════════════════════
        // El contexto NO sabe cómo calcular la ruta, simplemente le pide
        // a la estrategia que lo haga. La estrategia sabe cómo hacerlo.
        // 
        // Esto es INVERSIÓN DE DEPENDENCIAS:
        // - El contexto depende de la INTERFAZ (EstrategiaRuta)
        // - NO depende de las clases concretas
        // - Puedes agregar nuevas estrategias sin tocar el contexto
        
        System.out.println(" Contexto delegando cálculo a: " + 
                          estrategia.obtenerTipoTransporte());
        
        ResultadoRuta resultado = estrategia.calcularRuta(grafo, origen, destino);
        
        // Mensaje de finalización
        if (resultado != null && resultado.existeRuta()) {
            System.out.println(" Ruta calculada exitosamente\n");
        } else {
            System.out.println("  No se encontró ruta disponible\n");
        }
        
        return resultado;
    }
    
    /**
     * Método auxiliar para verificar si hay estrategia configurada
     */
    public boolean tieneEstrategia() {
        return estrategia != null;
    }
    
    /**
     * Método para obtener información de la estrategia actual
     */
    public String obtenerInfoEstrategiaActual() {
        if (estrategia == null) {
            return "Sin estrategia configurada";
        }
        return String.format("Estrategia actual: %s (%.1f km/h)",
                           estrategia.obtenerTipoTransporte(),
                           estrategia.obtenerVelocidadPromedio());
    }
    
    
    
    
}
