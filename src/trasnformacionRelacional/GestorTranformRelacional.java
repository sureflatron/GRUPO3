/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trasnformacionRelacional;

import gestionXMI.GestorXMI;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metamodeloDiagramClass.Asociacion;
import metamodeloDiagramClass.Atribute;
import metamodeloDiagramClass.Clase;
import metamodeloDiagramClass.ModeloClase;
import metamodeloDiagramClass.Paquete;
import metamodeloRelacional.Column;
import metamodeloRelacional.ForeignKey;
import metamodeloRelacional.Schema;
import metamodeloRelacional.Table;
import org.jdom.JDOMException;

/**
 *
 * @author Arturo
 */
public class GestorTranformRelacional {

    public Schema transformar(ModeloClase mc,String nameDataBase) {
        Schema sch = new Schema();
        Paquete p = mc.getPaquete();
        sch.setName(nameDataBase);
        int numClases = p.getCantidadClases();
        for (int i = 0; i < numClases; i++) {
            Clase c = p.getClase(i);
            if (c.isIspersistente()) {
                Table t = new Table();
                t.setName(c.getName());
                Column pk = new Column();
                pk.setName(c.getName() + "ID");
                pk.setType("int");
                t.setPrimaryKey(pk);
                List<Atribute> atributos = c.getAtributos();
                int m = atributos.size();
                for (int j = 0; j < m; j++) {
                    Column col = new Column();

                    Atribute a = atributos.get(j);
                    String nameAtributo = a.getType().getName();
                    col.setName(a.getName());
                    if (nameAtributo.equals("String")) {
                        col.setType("varchar(50)");
                    } else {
                        col.setType(a.getType().getName());
                    }
                    t.addColumna(col);
                }
                sch.addTabla(t);
            }
        }
        List<Asociacion> asociaciones = mc.getAsociaciones();
        int numAsociaciones = asociaciones.size();
        for (int i = 0; i < numAsociaciones; i++) {
            Asociacion a = asociaciones.get(i);
            Table origen = sch.getTable(a.getOrigen().getName());
            Table destino = sch.getTable(a.getDestino().getName());
            String multorigen = a.getMultiplicidadOrigen();
            String multdestino = a.getMultipliciadaDestino();


            if (multorigen.equals("0..1")) {
                ForeignKey fk = new ForeignKey();
                if (multdestino.equals("0..1")) {
                    
                    fk.addTabla(destino);
                    fk.addColumna(destino.getPrimaryKey());
                    fk.setName(destino.getName()+"ID");
                    origen.addLlaveForanea(fk);
                }
                if (multdestino.equals("1..*")) {

                    fk.addTabla(origen);
                    fk.addColumna(origen.getPrimaryKey());
                    fk.setName(origen.getName()+"ID");
                    destino.addLlaveForanea(fk);
                }
                if (multdestino.equals("0..*")) {

                    fk.addTabla(origen);
                    fk.addColumna(origen.getPrimaryKey());
                    fk.setName(origen.getName()+"ID");
                    destino.addLlaveForanea(fk);
                }
                if (multdestino.equals("1")||multdestino.equals("")) {
                    /*fk.addTabla(origen);
                     fk.addColumna(origen.getColumna(origen.getName()+"ID"));
                     destino.addLlaveForanea(fk);*/
                }

            } else {
                if (multorigen.equals("1..*")) {
                    ForeignKey fk = new ForeignKey();
                    if (multdestino.equals("0..1")) {

                        fk.addTabla(destino);
                        fk.addColumna(destino.getPrimaryKey());
                        fk.setName(destino.getName()+"ID");
                        origen.addLlaveForanea(fk);
                    }
                    if (multdestino.equals("1..*")) {
                        /*fk.addTabla(origen);
                         fk.addColumna(origen.getColumna(origen.getName()+"ID"));
                         destino.addLlaveForanea(fk);*/
                    }
                    if (multdestino.equals("0..*")) {
                        /*fk.addTabla(origen);
                         fk.addColumna(origen.getColumna(origen.getName()+"ID"));
                         destino.addLlaveForanea(fk);}*/
                    }
                    if (multdestino.equals("1")||multdestino.equals("")) {

                        fk.addTabla(destino);
                        fk.addColumna(destino.getPrimaryKey());
                        fk.setName(destino.getName()+"ID");
                        origen.addLlaveForanea(fk);
                    }

                } else {
                    if (multorigen.equals("0..*")) {
                        ForeignKey fk = new ForeignKey();
                        if (multdestino.equals("0..1")) {

                            fk.addTabla(destino);
                            fk.addColumna(destino.getPrimaryKey());
                            fk.setName(destino.getName()+"ID");
                            origen.addLlaveForanea(fk);
                        }
                        if (multdestino.equals("1..*")) {
                            /*fk.addTabla(origen);
                             fk.addColumna(origen.getColumna(origen.getName()+"ID"));
                             destino.addLlaveForanea(fk);*/
                        }
                        if (multdestino.equals("0..*")) {
                            /*fk.addTabla(origen);
                             fk.addColumna(origen.getColumna(origen.getName()+"ID"));
                             destino.addLlaveForanea(fk);}*/
                        }
                        if (multdestino.equals("1")||multdestino.equals("")) {

                            fk.addTabla(destino);
                            fk.addColumna(destino.getPrimaryKey());
                            fk.setName(destino.getName()+"ID");
                            origen.addLlaveForanea(fk);
                        }

                    } else {
                        if (multorigen.equals("1")||multorigen.equals("")) {
                            ForeignKey fk = new ForeignKey();
                            if (multdestino.equals("0..1")) {

                                fk.addTabla(destino);
                                fk.addColumna(destino.getPrimaryKey());
                                fk.setName(destino.getName()+"ID");
                                origen.addLlaveForanea(fk);
                            }
                            if (multdestino.equals("1..*")) {
                                fk.addTabla(origen);
                                 fk.addColumna(origen.getPrimaryKey());
                                 fk.setName(origen.getName()+"ID");
                                 destino.addLlaveForanea(fk);
                            }
                            if (multdestino.equals("0..*")) {
                                fk.addTabla(origen);
                                 fk.addColumna(origen.getPrimaryKey());
                                 fk.setName(origen.getName()+"ID");
                                 destino.addLlaveForanea(fk);
                            }
                            
                            if (multdestino.equals("1")) {

                                fk.addTabla(destino);
                                fk.addColumna(destino.getPrimaryKey());
                                fk.setName(destino.getName()+"ID");
                                origen.addLlaveForanea(fk);
                            }

                        }

                    }


                }
            }

        }

        return sch;
    }

    public static void main(String[] args) {
       /* GestorTranformRelacional gt = new GestorTranformRelacional();
        gestionXMI.GestorXMI gxmi = new GestorXMI();
        try {
            ModeloClase md = gxmi.importarDiagramaClases("C:/Users/Arturo/Documents/pim.xml");
            Schema sch = gt.transformar(md);
            int n = sch.getTablas().size();
            for (int i = 0; i < n; i++) {
                Table t = sch.getTablas().get(i);
                System.out.println(t.getName()+": ");
                int cantColumna=t.getColumnas().size();
                List<Column> columnas=t.getColumnas();
                for (int j = 0; j < cantColumna; j++) {
                    Column c=columnas.get(j);
                    System.out.println(c.getName()+" "+c.getType());
                }
                List<ForeignKey> foraneas=t.getLlavesForanneas();
                int cantidadForaneas=foraneas.size();
                for (int k = 0; k < cantidadForaneas; k++) {
                    ForeignKey f=foraneas.get(k);
                    Table tf=f.getTablas().get(0);
                    Column c2=f.getColumnas().get(0);
                    System.out.println(f.getName()+": references: "+tf.getName()+" "+c2.getName());
                }
                

            }

        } catch (JDOMException ex) {
            Logger.getLogger(GestorTranformRelacional.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorTranformRelacional.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
    }
}
