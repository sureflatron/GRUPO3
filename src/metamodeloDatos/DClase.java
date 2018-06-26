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
public class DClase extends DClassifier{
    private List<DField> fields=new ArrayList<>();
    private List<DMethod> metodos=new ArrayList<>();
    
    public void addFields(DField f){
        fields.add(f);
    }
    public void addMethod(DMethod c){
        metodos.add(c);
    }

    /**
     * @return the fields
     */
    public List<DField> getFields() {
        return fields;
    }

    /**
     * @return the metodos
     */
    public List<DMethod> getMetodos() {
        return metodos;
    }
    
}
