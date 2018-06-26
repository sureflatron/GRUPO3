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
public class Table {
    private String name;
    private List<Column> columnas=new ArrayList<>();
    private Column primaryKey;
    private List<ForeignKey> llavesForanneas=new ArrayList<>();

    public void addColumna(Column c){
        getColumnas().add(c);
    }
    public void addLlaveForanea(ForeignKey fk){
        getLlavesForanneas().add(fk);
    }
    public Column getColumna(String colName){
        Column c=null;
        int n=columnas.size();
        for (int i = 0; i < n; i++) {
            c=columnas.get(i);
            if (c.getName().equals(colName)) {
                return c;
            }
        }
        return c;
    }
    public Column getColumnaForanea(String colName){
        ForeignKey f=null;
        int n=llavesForanneas.size();
        for (int i = 0; i < n; i++) {
            f=llavesForanneas.get(i);
            if (f.getName().equals(colName)) {
                return f.getColumnas().get(0);
            }
        }
        return null;
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
     * @return the primaryKey
     */
    public Column getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey the primaryKey to set
     */
    public void setPrimaryKey(Column primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return the columnas
     */
    public List<Column> getColumnas() {
        return columnas;
    }

    /**
     * @return the llavesForanneas
     */
    public List<ForeignKey> getLlavesForanneas() {
        return llavesForanneas;
    }
    
}
