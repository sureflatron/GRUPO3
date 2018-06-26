/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloRelacional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo
 */
public class ForeignKey {
    private List<Column> columnas=new ArrayList<>();
    private List<Table> tablas=new ArrayList<>();
    private String name;
    
    
    public void addColumna(Column c){
        getColumnas().add(c);
    }
    public void addTabla(Table t){
        getTablas().add(t);
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
     * @return the columnas
     */
    public List<Column> getColumnas() {
        return columnas;
    }

    /**
     * @return the tablas
     */
    public List<Table> getTablas() {
        return tablas;
    }
}
