
package mapa;

/**
 *
 * @author gio
 */
public enum TipoVia {
    
    
    AUTOPISTA,      // Rápida para carros, no disponible para bicicletas/peatones
    CARRETERA,      // Media velocidad, disponible para carros y bicicletas
    CICLOVIA,       // Ideal para bicicletas, disponible para peatones
    SENDERO;        // Solo para peatones y bicicletas, no para carros
    
    /**
     * Verifica si un tipo de transporte puede usar esta vía
     * @param transporte Tipo de transporte
     * @return true si puede usar la vía
     */
    public boolean esTransitable(String transporte) {
        switch (this) {
            case AUTOPISTA:
                return transporte.equalsIgnoreCase("CARRO");
            case CARRETERA:
                return transporte.equalsIgnoreCase("CARRO") || 
                       transporte.equalsIgnoreCase("BICICLETA") ||
                       transporte.equalsIgnoreCase("CAMINANDO");
            case CICLOVIA:
                return transporte.equalsIgnoreCase("BICICLETA") || 
                       transporte.equalsIgnoreCase("CAMINANDO");
            case SENDERO:
                return transporte.equalsIgnoreCase("BICICLETA") || 
                       transporte.equalsIgnoreCase("CAMINANDO");
            default:
                return false;
        }
    }
    
    
}
