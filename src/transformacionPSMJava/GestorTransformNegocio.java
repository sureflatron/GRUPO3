/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transformacionPSMJava;

import gestionXMI.GestorXMI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metamodeloDatos.DClase;
import metamodeloDatos.DField;
import metamodeloDatos.DPaquete;
import metamodeloDiagramClass.Atribute;
import metamodeloDiagramClass.Clase;
import metamodeloDiagramClass.ModeloClase;
import metamodeloDiagramClass.Paquete;
import metamodeloNegocio.Controlador;
import metamodeloNegocio.NMetodos;
import metamodeloNegocio.NPaquete;
import metamodeloNegocio.NParametro;
import org.jdom.JDOMException;

/**
 *
 * @author Arturo
 */
public class GestorTransformNegocio {

    public NPaquete transformar(metamodeloDatos.DPaquete dm) {
        NPaquete np = new NPaquete();
        np.setName("negocio");
        int numClases = dm.getClases().size();
        for (int i = 0; i < numClases; i++) {
            DClase dc = dm.getClases().get(i);
           
                Controlador nc = new Controlador();
                nc.setName("Controlador" + dc.getName());
                nc.setNameEntidad(dc.getName());
                List<DField> atributos = dc.getFields();
                List<NParametro> parametros = new ArrayList<>();
                int m = atributos.size();
                for (int j = 0; j < m; j++) {
                    //atributo
                    DField a = atributos.get(j);

                    //cargar parametros pa constructor
                    NParametro npc = new NParametro();
                    npc.setName(a.getName());
                    npc.setType(a.getType().getName());
                    parametros.add(npc);

                }

                NMetodos registrar = new NMetodos();
                registrar.setName("registrar" + dc.getName());
                registrar.setRetorno(null);
                registrar.setParametros(parametros);
                nc.getMetodos().add(registrar);

                NMetodos modificar = new NMetodos();
                modificar.setName("modificar" + dc.getName());
                modificar.setRetorno(null);
                modificar.setParametros(parametros);
                nc.getMetodos().add(modificar);

                NMetodos eliminar = new NMetodos();
                eliminar.setName("eliminar" + dc.getName());
                eliminar.setRetorno(null);
                eliminar.getParametros().add(parametros.get(0));
                nc.getMetodos().add(eliminar);



                NMetodos buscaruno = new NMetodos();
                buscaruno.setName("buscaruno" + dc.getName());
                NParametro np1 = new NParametro();
                np1.setType(dc.getName());
                buscaruno.setRetorno(np1);
                buscaruno.getParametros().add(parametros.get(0));
                nc.getMetodos().add(buscaruno);

                NMetodos buscarall = new NMetodos();
                buscarall.setName("buscarall" + dc.getName());
                NParametro np2 = new NParametro();
                np2.setType("List<" + dc.getName() + ">");
                buscarall.setRetorno(np2);
                nc.getMetodos().add(buscarall);

                np.getControles().add(nc);
            
        }

        return np;
    }
    public static void main(String[] args) {
        GestorTransformNegocio gtn = new GestorTransformNegocio();
        gestionXMI.GestorXMI gxmi = new GestorXMI();
        ModeloClase md;
        try {
            md = gxmi.importarDiagramaClases("C:/Users/Arturo/Documents/pim.xml");
            GestorTransformJava gtj=new GestorTransformJava();
            DPaquete dp=gtj.transformar(md);
           NPaquete np= gtn.transformar(dp); 
            System.out.println(np.getName());
           int cantController=np.getControles().size();
            for (int i = 0; i < cantController; i++) {
                Controlador c=np.getControles().get(i);
                System.out.println(c.getName());
                int cantMetodos=c.getMetodos().size();
                for (int j = 0; j < cantMetodos; j++) {
                    NMetodos nm=c.getMetodos().get(j);
                    System.out.println(nm.getName());
                    int cantParametros=nm.getParametros().size();
                    for (int k = 0; k < cantParametros; k++) {
                        NParametro np1=nm.getParametros().get(k);
                        System.out.println(np1.getName()+" "+np1.getType());
                    }
                }
            }
            
        } catch (JDOMException ex) {
            Logger.getLogger(GestorTransformNegocio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorTransformNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

