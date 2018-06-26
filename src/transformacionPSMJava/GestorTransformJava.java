/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transformacionPSMJava;

import java.util.ArrayList;
import java.util.List;
import metamodeloDatos.*;
import metamodeloDiagramClass.*;

/**
 *
 * @author Arturo
 */
public class GestorTransformJava {

    public DPaquete transformar(ModeloClase mc) {
        DPaquete dp = new DPaquete();
        Paquete p = mc.getPaquete();
        dp.setName("datos");
        int numClases = p.getCantidadClases();
        for (int i = 0; i < numClases; i++) {
            Clase c = p.getClase(i);
            if (c.isIspersistente()) {
                DClase dc = new DClase();
                dc.setName(c.getName());
                DField df = new DField();
                df.setVisibility("private");
                df.setName(dc.getName().toLowerCase() + "ID");
                DClassifier dcl = new DClassifier();
                dcl.setName("int");
                df.setType(dcl);
                dc.addFields(df);

                List<Atribute> atributos = c.getAtributos();
                List<DParameter> parametrosConstructor = new ArrayList<>();
                int m = atributos.size();
                for (int j = 0; j < m; j++) {
                    //atributo
                    Atribute a = atributos.get(j);
                    DField df2 = new DField();
                    df2.setVisibility("private");
                    df2.setName(a.getName());
                    DClassifier dcl2 = new DClassifier();
                    /*if (a.getType().getName().equals("Date")) {
                        dcl2.setName("Calendar");
                    }else{
                        dcl2.setName(a.getType().getName());
                    }
                    */
                    
                    dcl2.setName(a.getType().getName());
                    df2.setType(dcl2);
                    dc.addFields(df2);
                    //cargar parametros pa constructor
                    DParameter dpc = new DParameter();
                    dpc.setName(df2.getName());
                    dpc.setType(df2.getType());
                    parametrosConstructor.add(dpc);
                    // metodo get 
                   /* DMethod get = new DMethod();
                    get.setName("get" + a.getName());
                    get.setVisibility("public");
                    DParameter p2 = new DParameter();
                    p2.setName("");
                    DClassifier dcl3 = new DClassifier();
                    dcl3.setName(a.getType().getName());
                    p2.setType(dcl3);
                    get.setRetorno(p2);
                    dc.getMetodos().add(get);
                    //metodo set
                    DMethod set = new DMethod();
                    set.setName("set" + a.getName());
                    set.setVisibility("public");
                    set.setRetorno(null);
                    DParameter p1 = new DParameter();
                    p1.setName(a.getName());
                    DClassifier dcl4 = new DClassifier();
                    dcl4.setName(a.getType().getName());
                    p1.setType(dcl4);
                    set.addParametro(p1);
                    dc.getMetodos().add(set);*/


                }
                /*DMethod getID = new DMethod();
                getID.setName("get" + dc.getName().toLowerCase() + "ID");
                getID.setVisibility("public");
                DParameter p2 = new DParameter();
                p2.setName("");
                DClassifier dcl3 = new DClassifier();
                dcl3.setName("int");
                p2.setType(dcl3);
                getID.setRetorno(p2);
                dc.getMetodos().add(getID);
                //metodo set
                DMethod setID = new DMethod();
                setID.setName("set" + dc.getName().toLowerCase() + "ID");
                setID.setVisibility("public");
                setID.setRetorno(null);
                DParameter p1 = new DParameter();
                p1.setName(dc.getName().toLowerCase() + "ID");
                DClassifier dcl4 = new DClassifier();
                dcl4.setName("int");
                p1.setType(dcl4);
                setID.addParametro(p1);
                dc.getMetodos().add(setID);*/



                DMethod constructor = new DMethod();
                constructor.setVisibility("public");
                constructor.setName(dc.getName());
                constructor.setRetorno(null);
                int x = parametrosConstructor.size();
                for (int j = 0; j < x; j++) {
                    constructor.addParametro(parametrosConstructor.get(j));
                }
                dc.getMetodos().add(constructor);
                dp.getClases().add(dc);
            }


        }

        List<Asociacion> asociaciones = mc.getAsociaciones();
        int numAsociaciones = asociaciones.size();
        for (int i = 0; i < numAsociaciones; i++) {
            Asociacion a = asociaciones.get(i);
            DClase origen = dp.getClase(a.getOrigen().getName());
            DClase destino = dp.getClase(a.getDestino().getName());
            String multorigen = a.getMultiplicidadOrigen();
            String multdestino = a.getMultipliciadaDestino();
            DClassifier dc = new DClassifier();
            dc.setName("int");

            if (multorigen.equals("0..1")) {
                DField fk = new DField();
                if (multdestino.equals("0..1")) {
                    fk.setVisibility("private");
                    fk.setName(destino.getName().toLowerCase() + "ID");
                    fk.setType(dc);
                    origen.addFields(fk);
                }
                if (multdestino.equals("1..*")) {

                    fk.setVisibility("private");
                    fk.setName(origen.getName().toLowerCase() + "ID");
                    fk.setType(dc);
                    destino.addFields(fk);
                }
                if (multdestino.equals("0..*")) {

                    fk.setVisibility("private");
                    fk.setName(origen.getName().toLowerCase() + "ID");
                    fk.setType(dc);
                    destino.addFields(fk);
                }
                if (multdestino.equals("1") || multdestino.equals("")) {
                    fk.setVisibility("private");
                    fk.setName(origen.getName().toLowerCase() + "ID");
                    fk.setType(dc);
                    destino.addFields(fk);
                }

            } else {
                if (multorigen.equals("1..*")) {
                    DField fk = new DField();
                    if (multdestino.equals("0..1")) {
                        fk.setVisibility("private");
                        fk.setName(destino.getName().toLowerCase() + "ID");
                        fk.setType(dc);
                        origen.addFields(fk);
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
                    if (multdestino.equals("1") || multdestino.equals("")) {

                        fk.setVisibility("private");
                        fk.setName(destino.getName().toLowerCase() + "ID");
                        fk.setType(dc);
                        origen.addFields(fk);
                    }

                } else {
                    if (multorigen.equals("0..*")) {
                        DField fk = new DField();
                        if (multdestino.equals("0..1")) {
                            fk.setVisibility("private");
                            fk.setName(destino.getName().toLowerCase() + "ID");
                            fk.setType(dc);
                            origen.addFields(fk);
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
                        if (multdestino.equals("1") || multdestino.equals("")) {
                            fk.setVisibility("private");
                            fk.setName(destino.getName().toLowerCase() + "ID");
                            fk.setType(dc);
                            origen.addFields(fk);
                        }

                    } else {
                        if (multorigen.equals("1") || multorigen.equals("")) {
                            DField fk = new DField();
                            if (multdestino.equals("0..1")) {
                                fk.setVisibility("private");
                                fk.setName(destino.getName().toLowerCase() + "ID");
                                fk.setType(dc);
                                origen.addFields(fk);
                            }
                            if (multdestino.equals("1..*")) {
                                fk.setVisibility("private");
                                fk.setName(origen.getName().toLowerCase() + "ID");
                                fk.setType(dc);
                                destino.addFields(fk);
                            }
                            if (multdestino.equals("0..*")) {
                                fk.setVisibility("private");
                                fk.setName(origen.getName().toLowerCase() + "ID");
                                fk.setType(dc);
                                destino.addFields(fk);
                            }

                            if (multdestino.equals("1")) {
                                fk.setVisibility("private");
                                fk.setName(destino.getName().toLowerCase() + "ID");
                                fk.setType(dc);
                                origen.addFields(fk);
                            }

                        }

                    }


                }
            }

        }
        int cantidadDC=dp.getClases().size();
        for (int i = 0; i < cantidadDC; i++) {
            DClase dc3=dp.getClases().get(i);
            List<DField> DFields = dc3.getFields();
                
                int m = DFields.size();
                for (int j = 0; j < m; j++) {
                    //atributo
                    DField df = DFields.get(j);
                    // metodo get 
                   DMethod get = new DMethod();
                    get.setName("get" + df.getName());
                    get.setVisibility("public");
                    DParameter p2 = new DParameter();
                    p2.setName("");
                    DClassifier dcl3 = new DClassifier();
                    dcl3.setName(df.getType().getName());
                    p2.setType(dcl3);
                    get.setRetorno(p2);
                    dc3.getMetodos().add(get);
                    //metodo set
                    DMethod set = new DMethod();
                    set.setName("set" + df.getName());
                    set.setVisibility("public");
                    set.setRetorno(null);
                    DParameter p1 = new DParameter();
                    p1.setName(df.getName());
                    DClassifier dcl4 = new DClassifier();
                    dcl4.setName(df.getType().getName());
                    p1.setType(dcl4);
                    set.addParametro(p1);
                    dc3.getMetodos().add(set);
                }            
        }

        return dp;
    }

    public static void main(String[] args) {
        /*  gestionXMI.GestorXMI gxmi = new GestorXMI();
         try {        
         ModeloClase md = gxmi.importarDiagramaClases("C:/Users/Arturo/Documents/pim.xml");
         GestorTransformJava gt=new GestorTransformJava();
         DPaquete dp=gt.transformar(md);
         int n=dp.getClases().size();
         for (int i = 0; i < n; i++) {
         DClase c=dp.getClases().get(i);
         System.out.println(c.getName());
         int m=c.getFields().size();
         for (int j = 0; j < m; j++) {
         DField df=c.getFields().get(j);
         System.out.println("atributos");
         System.out.println(df.getVisibility()+": "+ df.getType().getName()+" : "+df.getName());
         }
         System.out.println("metodos");
         int m1=c.getMetodos().size();
         for (int j = 0; j < m1; j++) {
         DMethod dm=c.getMetodos().get(j);
         DParameter dp1=dm.getRetorno();
         String x="";
         if (dp1==null) {
         x="void";
         }else{
         x=dp1.getType().getName();
         }
         System.out.println(dm.getVisibility()+" : "+x+" : "+dm.getName());
                    
         int m2=dm.getParameters().size();
         for (int k = 0; k < m2; k++) {
         System.out.println("parametros");
         DParameter dp2=dm.getParameters().get(k);
         System.out.println(dp2.getType().getName()+": "+dp2.getName());
         }
         }
                
         }
         } catch (JDOMException ex) {
         Logger.getLogger(GestorTransformJava.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
         Logger.getLogger(GestorTransformJava.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
    }
}
