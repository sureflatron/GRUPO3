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
public class DMethod {
    private String name;
    private String visibility;
    private List<DParameter> parameters=new ArrayList<>();
    private  DParameter retorno;
    
    public void addParametro(DParameter p){
        parameters.add(p);
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * @return the retorno
     */
    public DParameter getRetorno() {
        return retorno;
    }

    /**
     * @param retorno the retorno to set
     */
    public void setRetorno(DParameter retorno) {
        this.retorno = retorno;
    }

    /**
     * @return the parameters
     */
    public List<DParameter> getParameters() {
        return parameters;
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
