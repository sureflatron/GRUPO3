/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloDatos;

/**
 *
 * @author Arturo
 */
public class DTypedElement {
    private DClassifier type;
    private String name;

    /**
     * @return the type
     */
    public DClassifier getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(DClassifier type) {
        this.type = type;
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
