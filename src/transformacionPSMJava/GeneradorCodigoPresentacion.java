/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transformacionPSMJava;

import gestionXMI.GestorXMI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import metaModeloSwing.Componente;
import metaModeloSwing.Formulario;
import metaModeloSwing.PPaquete;
import metamodeloDatos.DPaquete;
import metamodeloDiagramClass.ModeloClase;
import org.jdom.JDOMException;

/**
 *
 * @author ELIO
 */
public class GeneradorCodigoPresentacion {
    public void generarCapaPresentacion(PPaquete pp, String path) {
        String direct = path + pp.getName();
        File directorio = new File(direct);
        directorio.mkdir();
        int cantidadControladores = pp.getFormularios().size();
        String linea = "";
        for (int i = 0; i < cantidadControladores; i++) {
            Formulario f = pp.getFormularios().get(i);
            PrintWriter salida = null;
            try {
                salida = new PrintWriter(direct + "/" + f.getName() + ".java");
                linea = "package " +pp.getName() + ";" + "\n";
                salida.println(linea);
                linea = "import javax.swing.GroupLayout;";
                salida.println(linea);
               /* linea = "import datos." + pc.getNameEntidad() + ";";
                salida.println(linea);*/
                linea = "import javax.swing.JFrame;";
                salida.println(linea);
                
                linea = "import javax.swing.JButton;";
                salida.println(linea);
                linea = "import javax.swing.JLabel;";
                salida.println(linea);
                
                linea = "import javax.swing.JTextField;";
                salida.println(linea);

                linea = "public class " + f.getName() + " extends JFrame{" + "\n";
                salida.println(linea);
                /*
                 int cantidadAtributos = nc.getFields().size();
                 for (int j = 0; j < cantidadAtributos; j++) {
                 DField df = nc.getFields().get(j);
                 linea = df.getVisibility() + " " + df.getType().getName() + " " + df.getName() + ";" + "\n";
                 salida.println(linea);

                 }
                 */
                int cantidadComponentes=f.getComponetes().size();
                for (int j = 0; j < cantidadComponentes; j++) {
                    Componente c= f.getComponetes().get(j);
                    linea="private "+c.getType()+" "+c.getName()+";";
                    salida.println(linea);
                    
                }
                linea = "public " + f.getName() + "(){";
                salida.println(linea);
                linea="setTitle(\""+f.getTexto()+"\");";
                salida.println(linea);
                linea="setBounds(0,0,"+f.getAncho()+","+f.getAlto()+");";
                salida.println(linea);
                linea="setLayout(new GroupLayout(getContentPane()));";
                salida.println(linea);
                linea="setVisible(true);";
                salida.println(linea);
                for (int j = 0; j < cantidadComponentes; j++) {
                    Componente c= f.getComponetes().get(j);
                    linea=c.getName()+" = new "+c.getType()+"();";
                    salida.println(linea);
                    linea=c.getName()+".setBounds("+c.getX()+","+c.getY()+","+c.getAncho()+","+c.getAlto()+");";
                    salida.println(linea);
                    if (!c.getType().equals("JComboBox")) {
                        
                     linea=c.getName()+".setText(\""+c.getTexto() +"\");";
                     salida.println(linea);
                     
                     
                    }
                    linea="getContentPane().add("+c.getName() +");";
                     salida.println(linea);
                    
                }
                linea="repaint();";
                salida.println(linea);
                linea="}";
                salida.println(linea);
                
                linea="public static void main(String[] args) {";
                salida.println(linea);
                linea=f.getName()+" "+f.getName().subSequence(0,1)+" = new "+f.getName()+"();";
                salida.println(linea);
                linea=f.getName().subSequence(0,1)+".setDefaultCloseOperation(EXIT_ON_CLOSE);";
                salida.println(linea);
                linea="}";
                salida.println(linea);
                linea="}";
                salida.println(linea);
                salida.flush();
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }

        }
    }
    public static void main(String[] args) {
        
        GeneradorCodigoPresentacion gcp=new GeneradorCodigoPresentacion();
        GestorTransformPresentacion gp=new GestorTransformPresentacion();
        gestionXMI.GestorXMI gxmi = new GestorXMI();
        ModeloClase md;
        try {
            md = gxmi.importarDiagramaClases("C:/Users/Arturo/Documents/pim.xml");
            GestorTransformJava gtj=new GestorTransformJava();
            DPaquete dp=gtj.transformar(md);
            PPaquete pp=gp.transformar(dp);
            gcp.generarCapaPresentacion(pp,"C:/Users/Arturo/Documents/");
            
          
            
        } catch (JDOMException ex) {
            Logger.getLogger(GestorTransformNegocio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GestorTransformNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
