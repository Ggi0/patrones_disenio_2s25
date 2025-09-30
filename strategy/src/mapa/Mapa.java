/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

/**
 * 
 *      Clase utilitaria para crear un mapa de ejemplo con 6 nodos
 *
 * @author gio
 */
public class Mapa {
    
    public static Grafo crearMapaDefault() {
        Grafo grafo = new Grafo();
        
        // === PASO 1: CREAR LOS 16 NODOS ===
        // Distribución orgánica en un área de 700x550
        
        // Zona Norte (superior)
        Nodo nodoA = new Nodo("A", 100, 80);    // Noroeste
        Nodo nodoB = new Nodo("B", 280, 100);   // Norte-centro
        Nodo nodoC = new Nodo("C", 450, 90);    // Norte
        Nodo nodoD = new Nodo("D", 620, 110);   // Noreste
        
        // Zona Centro-Norte
        Nodo nodoE = new Nodo("E", 150, 200);   // Centro-oeste-norte
        Nodo nodoF = new Nodo("F", 340, 210);   // Centro-norte
        Nodo nodoG = new Nodo("G", 520, 190);   // Centro-este-norte
        
        // Zona Centro
        Nodo nodoH = new Nodo("H", 90, 320);    // Oeste
        Nodo nodoI = new Nodo("I", 250, 330);   // Centro
        Nodo nodoJ = new Nodo("J", 420, 310);   // Centro-este
        Nodo nodoK = new Nodo("K", 600, 330);   // Este
        
        // Zona Sur
        Nodo nodoL = new Nodo("L", 140, 450);   // Suroeste
        Nodo nodoM = new Nodo("M", 310, 460);   // Sur-centro
        Nodo nodoN = new Nodo("N", 480, 440);   // Sur
        Nodo nodoO = new Nodo("O", 650, 470);   // Sureste
        Nodo nodoP = new Nodo("P", 370, 550);   // Sur extremo
        
        // Agregar todos los nodos
        grafo.agregarNodo(nodoA);
        grafo.agregarNodo(nodoB);
        grafo.agregarNodo(nodoC);
        grafo.agregarNodo(nodoD);
        grafo.agregarNodo(nodoE);
        grafo.agregarNodo(nodoF);
        grafo.agregarNodo(nodoG);
        grafo.agregarNodo(nodoH);
        grafo.agregarNodo(nodoI);
        grafo.agregarNodo(nodoJ);
        grafo.agregarNodo(nodoK);
        grafo.agregarNodo(nodoL);
        grafo.agregarNodo(nodoM);
        grafo.agregarNodo(nodoN);
        grafo.agregarNodo(nodoO);
        grafo.agregarNodo(nodoP);
        
        // === PASO 2: CREAR LAS ARISTAS (CONEXIONES) ===
        
        // AUTOPISTAS (solo carros) - Vías rápidas principales
        grafo.agregarArista(nodoA, nodoB, 4.5, TipoVia.AUTOPISTA);
        grafo.agregarArista(nodoC, nodoD, 3.8, TipoVia.AUTOPISTA);
        grafo.agregarArista(nodoF, nodoJ, 4.2, TipoVia.AUTOPISTA);
        grafo.agregarArista(nodoJ, nodoN, 5.0, TipoVia.AUTOPISTA);
        grafo.agregarArista(nodoG, nodoK, 4.6, TipoVia.AUTOPISTA);
        
        // CARRETERAS (carros, bicicletas, caminando) - Vías principales accesibles
        grafo.agregarArista(nodoB, nodoC, 3.5, TipoVia.CARRETERA);
        grafo.agregarArista(nodoA, nodoE, 3.2, TipoVia.CARRETERA);
        grafo.agregarArista(nodoE, nodoF, 4.0, TipoVia.CARRETERA);
        grafo.agregarArista(nodoF, nodoG, 3.8, TipoVia.CARRETERA);
        grafo.agregarArista(nodoD, nodoG, 3.4, TipoVia.CARRETERA);
        grafo.agregarArista(nodoE, nodoH, 3.6, TipoVia.CARRETERA);
        grafo.agregarArista(nodoE, nodoI, 3.0, TipoVia.CARRETERA);
        grafo.agregarArista(nodoI, nodoJ, 3.5, TipoVia.CARRETERA);
        grafo.agregarArista(nodoG, nodoJ, 3.3, TipoVia.CARRETERA);
        grafo.agregarArista(nodoJ, nodoK, 3.9, TipoVia.CARRETERA);
        grafo.agregarArista(nodoH, nodoL, 3.4, TipoVia.CARRETERA);
        grafo.agregarArista(nodoI, nodoM, 3.7, TipoVia.CARRETERA);
        grafo.agregarArista(nodoL, nodoM, 3.5, TipoVia.CARRETERA);
        grafo.agregarArista(nodoM, nodoN, 3.6, TipoVia.CARRETERA);
        grafo.agregarArista(nodoN, nodoO, 4.2, TipoVia.CARRETERA);
        grafo.agregarArista(nodoK, nodoO, 4.5, TipoVia.CARRETERA);
        
        // CICLOVÍAS (bicicletas y caminando) - Rutas especiales para ciclistas
        grafo.agregarArista(nodoB, nodoF, 3.0, TipoVia.CICLOVIA);
        grafo.agregarArista(nodoC, nodoF, 3.2, TipoVia.CICLOVIA);
        grafo.agregarArista(nodoF, nodoI, 3.4, TipoVia.CICLOVIA);
        grafo.agregarArista(nodoH, nodoI, 4.0, TipoVia.CICLOVIA);
        grafo.agregarArista(nodoI, nodoL, 3.8, TipoVia.CICLOVIA);
        grafo.agregarArista(nodoM, nodoP, 2.8, TipoVia.CICLOVIA);
        grafo.agregarArista(nodoN, nodoP, 3.3, TipoVia.CICLOVIA);
        
        // SENDEROS (solo bicicletas y caminando) - Atajos peatonales
        grafo.agregarArista(nodoA, nodoF, 5.5, TipoVia.SENDERO);
        grafo.agregarArista(nodoB, nodoE, 3.5, TipoVia.SENDERO);
        grafo.agregarArista(nodoC, nodoG, 3.0, TipoVia.SENDERO);
        grafo.agregarArista(nodoD, nodoK, 6.2, TipoVia.SENDERO);
        grafo.agregarArista(nodoE, nodoL, 6.0, TipoVia.SENDERO);
        grafo.agregarArista(nodoJ, nodoM, 4.5, TipoVia.SENDERO);
        grafo.agregarArista(nodoK, nodoN, 4.0, TipoVia.SENDERO);
        grafo.agregarArista(nodoL, nodoP, 3.5, TipoVia.SENDERO);
        
        // Conexiones adicionales para garantizar conectividad
        grafo.agregarArista(nodoB, nodoI, 5.0, TipoVia.CARRETERA);
        grafo.agregarArista(nodoC, nodoJ, 5.5, TipoVia.CARRETERA);
        
        grafo.imprimirGrafo();
        
        return grafo;
    }
    
    
}
