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
public class Clase extends Classifier{
    private  boolean ispersistente;
    private List<Atribute> atributos;

    public Clase() {
        atributos=new ArrayList<>();
    }
    public void addAtributo(Atribute a){
        getAtributos().add(a);
    }

    /**
     * @return the ispersistente
     */
    
    public boolean isIspersistente() {
        return ispersistente;
    }

    /**
     * @param ispersistente the ispersistente to set
     */
    public void setIspersistente(boolean ispersistente) {
        this.ispersistente = ispersistente;
    }

    /**
     * @return the atributos
     */
    public List<Atribute> getAtributos() {
        return atributos;
    }
    
}
