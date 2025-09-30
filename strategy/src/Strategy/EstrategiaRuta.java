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
 * @author gio
 */
public interface EstrategiaRuta {
    
    
    ResultadoRuta calcularRuta(Grafo grafo, Nodo origen, Nodo destino);
    
    String obtenerTipoTransporte();
    
    double obtenerVelocidadPromedio();
    
}
