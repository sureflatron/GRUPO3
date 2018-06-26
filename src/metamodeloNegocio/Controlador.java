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
public class Controlador {
    private String name;
    private List<NMetodos> metodos = new ArrayList<NMetodos>();
    private List<Atributo> atributos = new ArrayList<Atributo>();
    private String nameEntidad;



    /**
     * @return the metodos
     */
    public List<NMetodos> getMetodos() {
        return metodos;
    }

    /**
     * @param metodos the metodos to set
     */
    public void setMetodos(List<NMetodos> metodos) {
        this.metodos = metodos;
    }

    /**
     * @return the atributos
     */
    public List<Atributo> getAtributos() {
        return atributos;
    }

    /**
     * @param atributos the atributos to set
     */
    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
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

    /**
     * @return the nameEntidad
     */
    public String getNameEntidad() {
        return nameEntidad;
    }

    /**
     * @param nameEntidad the nameEntidad to set
     */
    public void setNameEntidad(String nameEntidad) {
        this.nameEntidad = nameEntidad;
    }
    

}
