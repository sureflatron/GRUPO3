/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transformacionPSMJava;

import gestionXMI.GestorXMI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metaModeloSwing.Componente;
import metaModeloSwing.Formulario;
import metaModeloSwing.PPaquete;
import metamodeloDatos.DClase;
import metamodeloDatos.DField;
import metamodeloDatos.DPaquete;
import metamodeloDiagramClass.ModeloClase;
import org.jdom.JDOMException;

/**
 *
 * @author ELIO
 */
public class GestorTransformPresentacion {
    public PPaquete transformar(metamodeloDatos.DPaquete dm){
        PPaquete pp=new PPaquete();
        pp.setName("presentacion");
        int cantidadClases=dm.getClases().size();
        for (int i = 0; i < cantidadClases; i++) {
            DClase c=dm.getClases().get(i);
            Formulario f=new Formulario();
            f.setTexto("Gestionar "+c.getName());
            f.setName("P"+c.getName());
            f.setType("JFrame");
            int x=30;
            int y=30;
            int cant=0;
            int cantidadAtribitos=c.getFields().size();
            for (int j = 0; j < cantidadAtribitos; j++) {
                DField df=c.getFields().get(j);
                Componente cmp=new Componente();
                Componente cmpLabel=new Componente();
                int p=df.getName().indexOf("ID");
                
                if (p>=0) {
                    String sub=df.getName().substring(0, p);
                    if (sub.equals(c.getName().toLowerCase())) {
                        cmpLabel.setTexto(df.getName());
                        cmpLabel.setName("Label"+df.getName());
                        cmpLabel.setType("JLabel");
                        cmpLabel.setAncho(100);
                        cmpLabel.setAlto(30);
                        cmpLabel.setX(x);
                        cmpLabel.setY(y);
                        x=x+100+30;
                        f.getComponetes().add(cmpLabel);
                        cmp.setName("Text"+df.getName());
                        cmp.setType("JTextField");
                        cmp.setTexto("");
                        cmp.setAncho(100);
                        cmp.setAlto(30);
                        cmp.setX(x);
                        cmp.setY(y);
                        f.getComponetes().add(cmp);
                        x=x+100+30;
                        cant=cant+1;
                        if (cant==2) {
                            y=y+80;
                            cant=0;
                            x=30;
                            
                        }
                        
                        
                        
                    }else{
                        cmpLabel.setTexto(df.getName());
                        cmpLabel.setName("Label"+df.getName());
                        cmpLabel.setType("JLabel");
                        cmpLabel.setAncho(100);
                        cmpLabel.setAlto(30);
                        cmpLabel.setX(x);
                        cmpLabel.setY(y);
                        x=x+100+30;
                        f.getComponetes().add(cmpLabel);
                        
                        cmp.setName(df.getName().substring(0,df.getName().length()-2));
                        cmp.setType("JComboBox");
                        cmp.setTexto("");
                        cmp.setAncho(100);
                        cmp.setAlto(30);
                        cmp.setX(x);
                        cmp.setY(y);
                        f.getComponetes().add(cmp);
                        x=x+100+30;
                        cant=cant+1;
                        if (cant==2) {
                            y=y+80;
                            cant=0;
                            x=30;
                            
                        }
                        
                    }
                }else{
                    if (df.getType().getName().toLowerCase().equals("boolean")) {
                        cmp.setName("Combo"+df.getName());
                        cmp.setType("JCheckBox");
                        cmp.setTexto(df.getName());
                        cmp.setAncho(100);
                        cmp.setAlto(30);
                        cmp.setX(x);
                        cmp.setY(y);
                        f.getComponetes().add(cmp);
                        x=x+160;
                        cant=cant+1;
                        if (cant==2) {
                            y=y+80;
                            cant=0;
                            x=30;
                            
                        }
                        
                    }else{
                        cmpLabel.setTexto(df.getName());
                        cmpLabel.setName("Label"+df.getName());
                        cmpLabel.setType("JLabel");
                        cmpLabel.setAncho(100);
                        cmpLabel.setAlto(30);
                        cmpLabel.setX(x);
                        cmpLabel.setY(y);
                        x=x+100+30;
                        f.getComponetes().add(cmpLabel);
                        cmp.setName("Text"+df.getName());
                        cmp.setType("JTextField");
                        cmp.setTexto("");
                        cmp.setAncho(100);
                        cmp.setAlto(30);
                        cmp.setX(x);
                        cmp.setY(y);
                        f.getComponetes().add(cmp);
                        x=x+100+30;
                        cant=cant+1;
                        if (cant==2) {
                            y=y+80;
                            cant=0;
                            x=30;
                            
                        }
                        
                    }
                }
            }
            y=y+80;
            x=30;
            Componente butonLimpiar=new Componente();
            butonLimpiar.setType("JButton");
            butonLimpiar.setTexto("Limpiar");
            butonLimpiar.setName("limpiar");
            butonLimpiar.setAncho(100);
            butonLimpiar.setAlto(50);
            butonLimpiar.setX(x);
            butonLimpiar.setY(y);
            f.getComponetes().add(butonLimpiar);
            x=x+130;
            Componente butonRegistrar=new Componente();
            butonRegistrar.setType("JButton");
            butonRegistrar.setTexto("Registrar");
            butonRegistrar.setName("registrar");
            butonRegistrar.setAncho(100);
            butonRegistrar.setAlto(50);
            butonRegistrar.setX(x);
            butonRegistrar.setY(y);
            f.getComponetes().add(butonRegistrar);
            x=x+130;
            Componente butonModificar=new Componente();
            butonModificar.setType("JButton");
            butonModificar.setTexto("Modificar");
            butonModificar.setName("modificar");
            butonModificar.setAncho(100);
            butonModificar.setAlto(50);
            butonModificar.setX(x);
            butonModificar.setY(y);
            f.getComponetes().add(butonModificar);
            x=x+130;
            Componente butonEliminar=new Componente();
            butonEliminar.setType("JButton");
            butonEliminar.setTexto("Eliminar");
            butonEliminar.setName("elimanar");
            butonEliminar.setAncho(100);
            butonEliminar.setAlto(50);
            butonEliminar.setX(x);
            butonEliminar.setY(y);
            f.getComponetes().add(butonEliminar);
            x=x+130;
            Componente butonBuscar=new Componente();
            butonBuscar.setType("JButton");
            butonBuscar.setTexto("Buscar");
            butonBuscar.setName("buscar");
            butonBuscar.setAncho(100);
            butonBuscar.setAlto(50);
            butonBuscar.setX(x);
            butonBuscar.setY(y);
            f.getComponetes().add(butonBuscar);
            f.setAncho(x+140);
            f.setAlto(y+100);
            pp.getFormularios().add(f);
            
            
        }
        
        return pp;
    }
    public static void main(String[] args) {
        GestorTransformPresentacion gp=new GestorTransformPresentacion();
        gestionXMI.GestorXMI gxmi = new GestorXMI();
        ModeloClase md;
        try {
            md = gxmi.importarDiagramaClases("C:/Users/Arturo/Documents/pim.xml");
            GestorTransformJava gtj=new GestorTransformJava();
            DPaquete dp=gtj.transformar(md);
            PPaquete pp=gp.transformar(dp);
            int cantidadForm=pp.getFormularios().size();
            for (int i = 0; i < cantidadForm; i++) {
                Formulario f=pp.getFormularios().get(i);
                System.out.println(f.getName());
                int cantComponentes=f.getComponetes().size();
                for (int j = 0; j < cantComponentes; j++) {
                    Componente c=f.getComponetes().get(j);
                    System.out.println(c.getName());
                }
                
            }
          
            
        } catch (JDOMException ex) {
            Logger.getLogger(GestorTransformNegocio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorTransformNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
