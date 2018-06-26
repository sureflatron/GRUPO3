/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metaModeloSwing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ELIO
 */
public class PPaquete {
    private String name;
    private List<Formulario> formularios=new ArrayList<>();

    /**
     * @return the formularios
     */
    public List<Formulario> getFormularios() {
        return formularios;
    }

    /**
     * @param formularios the formularios to set
     */
    public void setFormularios(List<Formulario> formularios) {
        this.formularios = formularios;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
