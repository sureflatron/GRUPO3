/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloDiagramClass;

/**
 *
 * @author ELIO
 */
public class Atribute {
    private String name;
    private Classifier type;

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
     * @return the type
     */
    public Classifier getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Classifier type) {
        this.type = type;
    }
    
}
