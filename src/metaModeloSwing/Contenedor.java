/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metaModeloSwing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class Contenedor extends Componente{
private List<Componente> componetes=new ArrayList<>();

    /**
     * @return the componetes
     */
    public List<Componente> getComponetes() {
        return componetes;
    }

    /**
     * @param componetes the componetes to set
     */
    public void setComponetes(List<Componente> componetes) {
        this.componetes = componetes;
    }


}
