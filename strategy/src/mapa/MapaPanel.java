/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * Panel personalizado para dibujar el mapa (grafo)
 * Dibuja los nodos como círculos y las aristas como líneas
 * 
 * @author gio
 */
public class MapaPanel extends JPanel{
    
    // Atributos
    private Grafo grafo;                    // El grafo a dibujar
    private List<Nodo> rutaResaltada;       // Ruta a resaltar en el mapa
    
    // Constantes de diseño
    private final int RADIO_NODO = 25;      // Radio de los círculos de nodos
    private final int GROSOR_LINEA = 2;     // Grosor de las líneas normales
    private final int GROSOR_RUTA = 4;      // Grosor de la ruta resaltada
    
    // Colores
    private final Color COLOR_NODO = new Color(52, 152, 219);        // Azul
    private final Color COLOR_NODO_BORDE = new Color(41, 128, 185);  // Azul oscuro
    private final Color COLOR_ARISTA = new Color(149, 165, 166);     // Gris
    private final Color COLOR_RUTA = new Color(231, 76, 60);         // Rojo
    private final Color COLOR_TEXTO = Color.WHITE;
    
    
    /**
     * Constructor
     * @param grafo El grafo a visualizar
     */
    public MapaPanel(Grafo grafo) {
        this.grafo = grafo;
        this.rutaResaltada = null;
        
        // Configuración del panel
        setBackground(new Color(236, 240, 241));  // Fondo gris claro
        setPreferredSize(new Dimension(800, 600));
    }
    
    
    /**
     * Establece una ruta para resaltar en el mapa
     * @param ruta Lista de nodos que forman la ruta
     */
    public void setRutaResaltada(List<Nodo> ruta) {
        this.rutaResaltada = ruta;
        repaint();  // Redibuja el panel
    }
    
    
    /**
     * Limpia la ruta resaltada
     */
    public void limpiarRuta() {
        this.rutaResaltada = null;
        repaint();
    }
    
    /**
     * Método principal de dibujo
     * Se llama automáticamente cuando el panel necesita repintarse
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Activar anti-aliasing para dibujo suave
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        // 1. Dibujar todas las aristas (líneas)
        dibujarAristas(g2d);
        
        // 2. Dibujar la ruta resaltada (si existe)
        if (rutaResaltada != null && rutaResaltada.size() > 1) {
            dibujarRutaResaltada(g2d);
        }
        
        // 3. Dibujar todos los nodos (círculos)
        dibujarNodos(g2d);
    }
    
    /**
     * Dibuja todas las aristas del grafo como líneas
     */
    private void dibujarAristas(Graphics2D g2d) {
        g2d.setColor(COLOR_ARISTA);
        g2d.setStroke(new BasicStroke(GROSOR_LINEA));
        
        for (Arista arista : grafo.getAristas()) {
            Nodo origen = arista.getOrigen();
            Nodo destino = arista.getDestino();
            
            // Dibuja línea entre los centros de los nodos
            g2d.drawLine(origen.getX(), origen.getY(), 
                        destino.getX(), destino.getY());
            
            // Opcional: Dibuja la distancia en el medio de la línea
            dibujarDistancia(g2d, arista);
        }
    }
    
    /**
     * Dibuja la distancia en el punto medio de una arista
     */
    private void dibujarDistancia(Graphics2D g2d, Arista arista) {
        Nodo origen = arista.getOrigen();
        Nodo destino = arista.getDestino();
        
        // Calcula el punto medio
        int medioX = (origen.getX() + destino.getX()) / 2;
        int medioY = (origen.getY() + destino.getY()) / 2;
        
        // Dibuja el texto de la distancia
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        String texto = String.format("%.1f km", arista.getDistancia());
        g2d.drawString(texto, medioX - 20, medioY - 5);
    }
    
    /**
     * Dibuja la ruta resaltada con color especial
     */
    private void dibujarRutaResaltada(Graphics2D g2d) {
        g2d.setColor(COLOR_RUTA);
        g2d.setStroke(new BasicStroke(GROSOR_RUTA));
        
        // Dibuja líneas entre cada par consecutivo de nodos en la ruta
        for (int i = 0; i < rutaResaltada.size() - 1; i++) {
            Nodo actual = rutaResaltada.get(i);
            Nodo siguiente = rutaResaltada.get(i + 1);
            
            g2d.drawLine(actual.getX(), actual.getY(), 
                        siguiente.getX(), siguiente.getY());
        }
    }
    
    /**
     * Dibuja todos los nodos como círculos con sus nombres
     */
    private void dibujarNodos(Graphics2D g2d) {
        for (Nodo nodo : grafo.getNodos()) {
            // Dibuja el círculo del nodo
            g2d.setColor(COLOR_NODO);
            g2d.fillOval(nodo.getX() - RADIO_NODO, 
                        nodo.getY() - RADIO_NODO, 
                        RADIO_NODO * 2, 
                        RADIO_NODO * 2);
            
            // Dibuja el borde del círculo
            g2d.setColor(COLOR_NODO_BORDE);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(nodo.getX() - RADIO_NODO, 
                        nodo.getY() - RADIO_NODO, 
                        RADIO_NODO * 2, 
                        RADIO_NODO * 2);
            
            // Dibuja el nombre del nodo
            g2d.setColor(COLOR_TEXTO);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g2d.getFontMetrics();
            int textoAncho = fm.stringWidth(nodo.getNombre());
            int textoAlto = fm.getAscent();
            
            g2d.drawString(nodo.getNombre(), 
                          nodo.getX() - textoAncho / 2, 
                          nodo.getY() + textoAlto / 4);
        }
    }
    
    
    
}
