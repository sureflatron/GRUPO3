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
public class NMetodos {
    private String name;
    private List<NParametro> parametros = new ArrayList<NParametro>();
    private NParametro retorno;

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
     * @return the parametros
     */
    public List<NParametro> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(List<NParametro> parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the retorno
     */
    public NParametro getRetorno() {
        return retorno;
    }

    /**
     * @param retorno the retorno to set
     */
    public void setRetorno(NParametro retorno) {
        this.retorno = retorno;
    }
}
