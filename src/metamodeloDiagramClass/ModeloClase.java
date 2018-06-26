/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloDiagramClass;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ELIO
 */
public class ModeloClase {
   private List<PrimitiveDataType> typesData;
   private Paquete paquete;
   private List<Asociacion> asociaciones;

    public ModeloClase() {
        typesData=new ArrayList<>();
        asociaciones=new ArrayList<>();
                
    }

    /**
     * @return the typesData
     */
    public List<PrimitiveDataType> getTypesData() {
        return typesData;
    }

    /**
     * @param typesData the typesData to set
     */
    public void setTypesData(List<PrimitiveDataType> typesData) {
        this.typesData = typesData;
    }

    /**
     * @return the paquete
     */
    public Paquete getPaquete() {
        return paquete;
    }

    /**
     * @param paquete the paquete to set
     */
    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    /**
     * @return the asociaciones
     */
    public List<Asociacion> getAsociaciones() {
        return asociaciones;
    }

    /**
     * @param asociaciones the asociaciones to set
     */
    public void setAsociaciones(List<Asociacion> asociaciones) {
        this.asociaciones = asociaciones;
    }
   
    
}
