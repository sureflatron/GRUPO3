/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trasnformacionRelacional;

import gestionXMI.GestorXMI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metamodeloDiagramClass.ModeloClase;
import metamodeloRelacional.Column;
import metamodeloRelacional.ForeignKey;
import metamodeloRelacional.Schema;
import metamodeloRelacional.Table;
import org.jdom.JDOMException;

/**
 *
 * @author Arturo
 */
public class GeneradorScript {
    public void crearScript(Schema s,String path){
        PrintWriter salida = null;
        try {
            salida = new PrintWriter(path);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
                        
        String sc ="";
        String dbname =s.getName();
        sc =  "create database "+ dbname+ ";"+"\n";
        salida.println(sc);
                        
        sc =  "use "+ dbname+";"+"\n";
        salida.println(sc);
        int n = s.getTablas().size();
        for (int i = 0; i < n; i++) {
            Table t = s.getTablas().get(i);
            
            sc= "create table "+ t.getName()+"( ";
            sc= sc+ t.getPrimaryKey().getName()+ " " + t.getPrimaryKey().getType()+ " not null primary key auto_increment, ";
            int m = t.getColumnas().size();
            for (int j = 0; j < m; j++) {
                sc = sc + t.getColumnas().get(j).getName()+ " "+ t.getColumnas().get(j).getType()+" ,";
            }
            if(t.getLlavesForanneas().size()==0)
            sc=sc.substring(0, sc.length()-1);
            
            int q=t.getLlavesForanneas().size();
            for (int k = 0; k < q; k++) {
                ForeignKey fk=t.getLlavesForanneas().get(k);
                sc=sc+fk.getName()+ " "+ fk.getColumnas().get(0).getType()+" ,";
            }
            sc=sc.substring(0, sc.length()-1);
            sc=sc+");"+"\n";
            salida.println(sc);
            
        }
        n = s.getTablas().size();
        
        for (int i = 0; i < n; i++) {
            
            Table t = s.getTablas().get(i);
            List<ForeignKey> foranea= t.getLlavesForanneas();
            int m = foranea.size();
            for (int j = 0; j < m; j++) {
                sc="";
                ForeignKey fk = foranea.get(j);
                sc= sc +"alter table "+ t.getName();
                sc= sc +" add constraint "+ fk.getName()+ " foreign key ( ";
                sc= sc +fk.getName()+") references "+ fk.getTablas().get(0).getName();
                sc= sc +"( "+ fk.getColumnas().get(0).getName()+"); "+"\n";
                salida.println(sc);
            }
            //sc= sc + "
            
        }        
       // ALTER TABLE `pelicula`
        // ADD CONSTRAINT `pelicula_ibfk_1` FOREIGN KEY (`idgenero`) REFERENCES `genero` (`idgenero`);
  
       salida.flush();
       
    }
    public static void main(String[] args) {
       /* GeneradorScript g = new GeneradorScript();
        GestorTranformRelacional gt = new GestorTranformRelacional();
        gestionXMI.GestorXMI gxmi = new GestorXMI();
        try {
            ModeloClase md = gxmi.importarDiagramaClases("D:/cc/pim.xml");
            Schema sch = gt.transformar(md);
            g.crearScript(sch);

        } catch (JDOMException ex) {
            Logger.getLogger(GestorTranformRelacional.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorTranformRelacional.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}

