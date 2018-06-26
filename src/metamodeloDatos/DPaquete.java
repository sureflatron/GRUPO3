/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloDatos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo
 */
public class DPaquete {
    private String name;
    private List<DClase> clases=new ArrayList<>();
    public void addClase(DClase c){
        clases.add(c);
    }
    
     public DClase getClase(String nameClase){
        DClase c=null;
        int n=clases.size();
        for (int i = 0; i < n; i++) {
             c=clases.get(i);
            if (c.getName().equals(nameClase)) {
                return c;
            }
            
            
        }
        return c;
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
     * @return the clases
     */
    public List<DClase> getClases() {
        return clases;
    }
    
}
