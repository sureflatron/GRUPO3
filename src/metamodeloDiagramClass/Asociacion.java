/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloDiagramClass;

/**
 *
 * @author ELIO
 */
public class Asociacion {
    private Clase origen;
    private Clase destino;
    private String nombre;
    private String multiplicidadOrigen;
    private String multipliciadaDestino;
    

    /**
     * @return the origen
     */
    public Clase getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Clase origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Clase getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Clase destino) {
        this.destino = destino;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the multiplicidadOrigen
     */
    public String getMultiplicidadOrigen() {
        return multiplicidadOrigen;
    }

    /**
     * @param multiplicidadOrigen the multiplicidadOrigen to set
     */
    public void setMultiplicidadOrigen(String multiplicidadOrigen) {
        this.multiplicidadOrigen = multiplicidadOrigen;
    }

    /**
     * @return the multipliciadaDestino
     */
    public String getMultipliciadaDestino() {
        return multipliciadaDestino;
    }

    /**
     * @param multipliciadaDestino the multipliciadaDestino to set
     */
    public void setMultipliciadaDestino(String multipliciadaDestino) {
        this.multipliciadaDestino = multipliciadaDestino;
    }

    
    
    
    
}
