/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloNegocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo
 */
public class NPaquete {
    private String name;
    private List<Controlador> controles = new ArrayList<Controlador>();

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

    /**
     * @return the controles
     */
    public List<Controlador> getControles() {
        return controles;
    }

    /**
     * @param controles the controles to set
     */
    public void setControles(List<Controlador> controles) {
        this.controles = controles;
    }

}