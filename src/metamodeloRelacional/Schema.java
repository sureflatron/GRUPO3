/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloRelacional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lab. redes
 */
public class Schema {
    private String name;
    private List<Table> tablas=new ArrayList<>();

    public void addTabla(Table t){
        getTablas().add(t);
    }
    public Table getTable(String nameTable){
        Table t=null;
        int n=tablas.size();
        for (int i = 0; i < n; i++) {
            t=tablas.get(i);
            if (t.getName().equals(nameTable)) {
                return t;
            }
            
            
        }
        return t;
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
     * @return the tablas
     */
    public List<Table> getTablas() {
        return tablas;
    }
    
}
