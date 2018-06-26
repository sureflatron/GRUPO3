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
import metamodeloDatos.*;
import metamodeloDiagramClass.ModeloClase;
import metamodeloNegocio.Controlador;
import metamodeloNegocio.NMetodos;
import metamodeloNegocio.NPaquete;
import metamodeloNegocio.NParametro;
import org.jdom.JDOMException;

/**
 *
 * @author Arturo
 */
public class GeneradorCodigoNegocio {

    public void generarCapaNegocio(NPaquete np, String path) {
        String direct = path + np.getName();
        File directorio = new File(direct);
        directorio.mkdir();
        int cantidadControladores = np.getControles().size();
        String linea = "";
        for (int i = 0; i < cantidadControladores; i++) {
            Controlador nc = np.getControles().get(i);
            PrintWriter salida = null;
            try {
                salida = new PrintWriter(direct + "/" + nc.getName() + ".java");
                linea = "package " + np.getName() + ";" + "\n";
                salida.println(linea);
                linea = "import java.util.List;";
                salida.println(linea);
                linea = "import datos." + nc.getNameEntidad() + ";";
                salida.println(linea);
                linea = "import java.util.ArrayList;";
                salida.println(linea);
                linea = "public class " + nc.getName() + "{" + "\n";
                salida.println(linea);
                linea = "public " + nc.getName() + "(){}";
                salida.println(linea);
                int cantidadMetodos = nc.getMetodos().size();
                for (int j = 0; j < cantidadMetodos; j++) {
                    NMetodos nm = nc.getMetodos().get(j);
                    String nameMetodo = nm.getName();
                    String retorno = "";
                    if (!nameMetodo.equals(nc.getName())) {
                        if (nm.getRetorno() == null) {
                            retorno = "void";
                        } else {
                            retorno = nm.getRetorno().getType();
                        }
                    }
                    //linea = nm.getVisibility() + " " + retorno + " " + nm.getName() + "(";
                    linea = "public" + " " + retorno + " " + nm.getName() + "(";
                    int cantidadParametros = nm.getParametros().size();
                    for (int k = 0; k < cantidadParametros; k++) {
                        NParametro np1 = nm.getParametros().get(k);
                        linea = linea + np1.getType() + " " + np1.getName() + ",";
                    }
                    if (cantidadParametros > 0) {
                        linea = linea.substring(0, linea.length() - 1);
                    }
                    linea = linea + "){";//+ "\n";
                    salida.println(linea);

                    if (nm.getName().indexOf("registrar") >= 0) {
                        linea = nc.getNameEntidad() + " " + nc.getNameEntidad().toLowerCase() + "=new " + nc.getNameEntidad() + "();";
                        salida.println(linea);
                        int cantParametros = nm.getParametros().size();
                        for (int k = 0; k < cantParametros; k++) {
                            NParametro np2 = nm.getParametros().get(k);
                            linea = nc.getNameEntidad().toLowerCase() + ".set" + np2.getName() + "(" + np2.getName() + ");";
                            salida.println(linea);
                        }
                        linea = nc.getNameEntidad().toLowerCase() + ".registrar" + nc.getNameEntidad() + "();";
                        salida.println(linea);
                    }

                    if (nm.getName().indexOf("modificar") >= 0) {
                        linea = nc.getNameEntidad() + " " + nc.getNameEntidad().toLowerCase() + "=new " + nc.getNameEntidad() + "();";
                        salida.println(linea);
                        int cantParametros = nm.getParametros().size();
                        for (int k = 0; k < cantParametros; k++) {
                            NParametro np2 = nm.getParametros().get(k);
                            linea = nc.getNameEntidad().toLowerCase() + ".set" + np2.getName() + "(" + np2.getName() + ");";
                            salida.println(linea);
                        }
                        linea = nc.getNameEntidad().toLowerCase() + ".modificar" + nc.getNameEntidad() + "();";
                        salida.println(linea);
                    }


                    if (nm.getName().indexOf("buscaruno") >= 0) {
                        linea = nc.getNameEntidad() + " " + nc.getNameEntidad().toLowerCase() + "=new " + nc.getNameEntidad() + "();";
                        salida.println(linea);
                        linea = "return " + nc.getNameEntidad().toLowerCase() + ".select" + nc.getNameEntidad() + "(" + nm.getParametros().get(0).getName() + ");";
                        salida.println(linea);
                    }
                    if (nm.getName().indexOf("buscarall") >= 0) {
                        //*
                        linea = nc.getNameEntidad() + " " + nc.getNameEntidad().toLowerCase() + "=new " + nc.getNameEntidad() + "();";
                        salida.println(linea);
                        linea = "return " + nc.getNameEntidad().toLowerCase() + ".selectAll" + nc.getNameEntidad() + "s();";
                        salida.println(linea);
                        //*/
                    }
                    if (nm.getName().indexOf("eliminar") >= 0) {
                        linea = nc.getNameEntidad() + " " + nc.getNameEntidad().toLowerCase() + "=new " + nc.getNameEntidad() + "();";
                        salida.println(linea);
                        linea = nc.getNameEntidad().toLowerCase() + ".eliminar" + nc.getNameEntidad() + "(" + nm.getParametros().get(0).getName() + ");";
                        salida.println(linea);
                    }

                    linea = "}" + "\n";
                    salida.println(linea);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            linea = "}" + "\n";
            salida.println(linea);
            salida.flush();
        }
    }

    public static void main(String[] args) {
        try {
            GestorTransformNegocio gtn = new GestorTransformNegocio();
            gestionXMI.GestorXMI gxmi = new GestorXMI();
            ModeloClase md;
            md = gxmi.importarDiagramaClases("C:/Users/Arturo/Documents/pim.xml");
            GestorTransformJava gtj = new GestorTransformJava();
            DPaquete dp = gtj.transformar(md);
            NPaquete np = gtn.transformar(dp);
            GeneradorCodigoNegocio gn = new GeneradorCodigoNegocio();
            gn.generarCapaNegocio(np, "C:/Users/Arturo/Documents/");
        } catch (JDOMException ex) {
            Logger.getLogger(GeneradorCodigoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorCodigoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
