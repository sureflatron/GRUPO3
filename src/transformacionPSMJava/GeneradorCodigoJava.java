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
import org.jdom.JDOMException;

/**
 *
 * @author estudiante
 */
public class GeneradorCodigoJava {

    public void generarCapaDatos(DPaquete dp, String path, String nameDatabase, String user, String password) {
        String direct = path + dp.getName();
        File directorio = new File(direct);
        directorio.mkdir();
        int cantidadClases = dp.getClases().size();
        String linea = "";
        for (int i = 0; i < cantidadClases; i++) {
            DClase dc = dp.getClases().get(i);
            PrintWriter salida = null;
            try {
                salida = new PrintWriter(direct + "/" + dc.getName() + ".java");
                linea = "package " + dp.getName() + ";" + "\n";
                salida.println(linea);
                linea = "import java.sql.ResultSet;";
                salida.println(linea);
                linea = "import java.sql.SQLException;";
                salida.println(linea);                
                linea = "import java.util.List;";
                salida.println(linea);
                linea = "import java.util.ArrayList;";
                salida.println(linea);
                linea = "import java.util.Date;";
                salida.println(linea);
                linea = "public class " + dc.getName() + "{" + "\n";
                salida.println(linea);
                int cantidadAtributos = dc.getFields().size();
                for (int j = 0; j < cantidadAtributos; j++) {
                    DField df = dc.getFields().get(j);
                    linea = df.getVisibility() + " " + df.getType().getName() + " " + df.getName() + ";" + "\n";
                    salida.println(linea);
                }
                linea = "public " + dc.getName() + "(){}";
                salida.println(linea);
                int cantidadMetodos = dc.getMetodos().size();
                for (int j = 0; j < cantidadMetodos; j++) {
                    DMethod dm = dc.getMetodos().get(j);
                    String nameMetodo = dm.getName();
                    String retorno = "";
                    if (!nameMetodo.equals(dc.getName())) {
                        if (dm.getRetorno() == null) {
                            retorno = "void";
                        } else {
                            retorno = dm.getRetorno().getType().getName();
                        }
                    }
                    linea = dm.getVisibility() + " " + retorno + " " + dm.getName() + "(";
                    int cantidadParametros = dm.getParameters().size();
                    for (int k = 0; k < cantidadParametros; k++) {
                        DParameter d = dm.getParameters().get(k);
                        linea = linea + d.getType().getName() + " " + d.getName() + ",";
                    }
                    if (cantidadParametros > 0) {
                        linea = linea.substring(0, linea.length() - 1);
                    }

                    linea = linea + "){" + "\n";
                    salida.println(linea);
                    if (dm.getName().equals(dc.getName())) {
                        for (int k = 0; k < cantidadParametros; k++) {
                            DParameter d = dm.getParameters().get(k);
                            linea = "this." + d.getName() + "= " + d.getName() + ";";
                            salida.println(linea);
                        }
                    } else {
                        if (retorno.equals("void")) {
                            linea = "this." + dm.getParameters().get(0).getName() + " = " + dm.getParameters().get(0).getName() + ";";
                            salida.println(linea);
                        } else {
                            linea = "return " + dm.getName().subSequence(3, dm.getName().length()) + ";";
                            salida.println(linea);
                        }
                    }
                    linea = "}" + "\n";
                    salida.println(linea);
                }
                // registrar
                linea = "public void registrar" + dc.getName() + "(){";
                salida.println(linea);
                linea = "Conexion con=new Conexion();";
                salida.println(linea);
                linea = "String s = \"insert into " + dc.getName() + " values (null,\"";
                int cantAtributos = dc.getFields().size();
                for (int j = 0; j < cantAtributos; j++) {
                    DField df = dc.getFields().get(j);
                    if (!df.getName().equals(dc.getName().toLowerCase() + "ID")) {
                        if (df.getType().getName().equals("String") || df.getType().getName().equals("Date") || df.getType().getName().equals("Time")) {
                            if (df.getType().getName().equals("Date")) {
                                linea = linea + "+\"'\"+" + df.getName() + ".getYear()+\"-\"+" + df.getName() + ".getMonth()+\"-\"+" + df.getName() + ".getDate()+" + "\"',\"";
                            } else {
                                linea = linea + "+\"'\"+" + df.getName() + ".toString()" + "+\"',\"";
                            }
                        } else {
                            linea = linea + "+" + df.getName() + "+\",\"";
                        }
                    }
                }
                linea = linea.substring(0, linea.length() - 2);
                linea = linea + ")\";";
                salida.println(linea);
                linea = "con.ejecutarConsulta(s);";
                salida.println(linea);
                linea = "con.closeConnection();";
                salida.println(linea);
                salida.println("}");
                //fin

                // modificar
                linea = "public void modificar" + dc.getName() + "(){";
                salida.println(linea);
                linea = "Conexion con=new Conexion();";
                salida.println(linea);
                linea = "String s = \"update " + dc.getName() + " set ";
                cantAtributos = dc.getFields().size();
                for (int j = 0; j < cantAtributos; j++) {
                    DField df = dc.getFields().get(j);
                    if (!df.getName().equals(dc.getName().toLowerCase() + "ID")) {

                        if (df.getType().getName().equals("Date")) {
                            linea = linea + df.getName() + "=\'\" + this." + df.getName() + ".getYear()+\"-\"+" + df.getName() + ".getMonth()+\"-\"+" + df.getName() + ".getDate()" + " + \"\',";
                        } else {
                            linea = linea + df.getName() + "=\'\" + this." + df.getName() + " + \"\',";
                        }
                    }
                }
                linea = linea.substring(0, linea.length() - 1);
                linea = linea + " where " + dc.getFields().get(0).getName() + " = \" + this." + dc.getFields().get(0).getName() + " + \";\";";
                salida.println(linea);
                linea = "con.ejecutarConsulta(s);";
                salida.println(linea);
                linea = "con.closeConnection();";         
                salida.println(linea);
                salida.println("}");
                //fin

                /*
                 public void eliminar() {
                 super.conectar();
                 super.ejecutarConsulta("delete from alumno where id_alumno = " + this.idAlumno + ";");
                 super.closeConnection();
                 } 
                 */

                // eliminar
                linea = "public void eliminar" + dc.getName() + "(int id){";
                salida.println(linea);
                linea = "Conexion con=new Conexion();";
                salida.println(linea);
                linea = "String s = \"delete from ";
                linea = linea + dc.getName()+ " where "+ dc.getFields().get(0).getName()+" = \" + id + \";\";";
                salida.println(linea);
                linea = "con.ejecutarConsulta(s);";
                salida.println(linea);
                linea = "con.closeConnection();";         
                salida.println(linea);
                salida.println("}");
                //fin

                // selectAll
                linea = "public List<" + dc.getName() + "> selectAll" + dc.getName() + "s(){";
                salida.println(linea);
                linea = "Conexion con=new Conexion();";
                salida.println(linea);
                linea = "ResultSet rs=con.obtenerDatos(\"SELECT * from " + dc.getName() + "\");";
                salida.println(linea);
                linea = "List<" + dc.getName() + "> " + dc.getName().toLowerCase() + "s" + "=new ArrayList<>();";
                salida.println(linea);
                salida.println("try {");
                linea = "while (rs.next()) {";
                salida.println(linea);
                int nfields = dc.getFields().size();
                linea = dc.getName() + " " + dc.getName().toLowerCase() + "=new " + dc.getName() + "();";
                salida.println(linea);
                for (int j = 0; j < nfields; j++) {
                    DField df = dc.getFields().get(j);
                    linea = dc.getName().toLowerCase() + ".set" + df.getName() + "((" + df.getType().getName() + ")" + "rs.getObject(\"" + df.getName() + "\"));";
                    salida.println(linea);
                }
                linea = dc.getName().toLowerCase() + "s.add(" + dc.getName().toLowerCase() + ");";
                salida.println(linea);
                salida.println("}");
                salida.println("} catch (SQLException ex) {}");
                salida.println("return " + dc.getName().toLowerCase() + "s;");
                salida.println("}");
                //fin

                // select uno  
                linea = "public " + dc.getName() + " select" + dc.getName() + "(int id){";
                salida.println(linea);
                linea = "Conexion con=new Conexion();";
                salida.println(linea);
                linea = "ResultSet rs=con.obtenerDatos(\"SELECT * from " + dc.getName() + " where " + dc.getName() + "ID =\" + id);";
                salida.println(linea);
                linea = dc.getName() + " " + dc.getName().toLowerCase() + "=null;";
                salida.println(linea);
                salida.println("try {");
                linea = "while (rs.next()) {";
                salida.println(linea);
                linea = dc.getName().toLowerCase() + "=new " + dc.getName() + "();";
                salida.println(linea);
                for (int j = 0; j < nfields; j++) {
                    DField df = dc.getFields().get(j);
                    linea = dc.getName().toLowerCase() + ".set" + df.getName() + "((" + df.getType().getName() + ")" + "rs.getObject(\"" + df.getName() + "\"));";
                    salida.println(linea);
                }
                salida.println("}");
                salida.println("} catch (SQLException ex) {}");
                salida.println("return " + dc.getName().toLowerCase() + ";");
                salida.println("}");
                salida.println("}");
                //fin

                salida.flush();
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
        }
        generarConexion(direct, nameDatabase, user, password);
    }

    private void generarConexion(String path, String nameDatabase, String user, String password) {
        PrintWriter salida = null;
        String linea = "";
        try {
            salida = new PrintWriter(path + "/" + "Conexion.java");
            linea = "package datos;" + "\n";
            salida.println(linea);
            linea = "import java.sql.Connection;" + "\n";
            salida.println(linea);
            linea = "import java.sql.DriverManager;" + "\n";
            salida.println(linea);
            linea = "import java.sql.ResultSet;" + "\n";
            salida.println(linea);
            linea = "import java.sql.Statement;" + "\n";
            salida.println(linea);
            linea = "public class Conexion {" + "\n";
            salida.println(linea);
            linea = "private Connection con = null;" + "\n";
            salida.println(linea);
            linea = "public Connection conectar() {" + "\n";
            salida.println(linea);
            linea = " try {" + "\n";
            salida.println(linea);
            linea = " Class.forName(\"com.mysql.jdbc.Driver\").newInstance();" + "\n";
            salida.println(linea);
            linea = "String databaseName = \"" + nameDatabase + "\";\n";
            salida.println(linea);
            linea = " String url = \"jdbc:mysql://localhost:3306/\" + databaseName" + ";" + "\n";
            salida.println(linea);
            linea = " con = DriverManager.getConnection(url, \"" + user + "\", \"" + password + "\");" + "\n";
            salida.println(linea);
            linea = "if (con != null)" + "\n";
            salida.println(linea);
            linea = "return con;" + "\n";
            salida.println(linea);
            linea = "} catch (Exception ex) {" + "\n";
            salida.println(linea);
            linea = "ex.printStackTrace();" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = " return con;" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = " public void closeConnection() {" + "\n";
            salida.println(linea);
            linea = " try {" + "\n";
            salida.println(linea);
            linea = " if (con != null)" + "\n";
            salida.println(linea);
            linea = " con.close();" + "\n";
            salida.println(linea);
            linea = " con = null;" + "\n";
            salida.println(linea);
            linea = "  } catch (Exception e) {" + "\n";
            salida.println(linea);
            linea = " e.printStackTrace();" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = " public ResultSet obtenerDatos(String consulta) {" + "\n";
            salida.println(linea);
            linea = " try {" + "\n";
            salida.println(linea);
            linea = "Connection con = conectar();" + "\n";
            salida.println(linea);
            linea = "  if (con != null) {" + "\n";
            salida.println(linea);
            linea = " Statement stmt = con.createStatement();" + "\n";
            salida.println(linea);
            linea = " return stmt.executeQuery(consulta);" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = "  } catch (Exception e) {" + "\n";
            salida.println(linea);
            linea = "e.printStackTrace();" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = " return null;" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = " public void ejecutarConsulta(String consulta) {" + "\n";
            salida.println(linea);
            linea = " try {" + "\n";
            salida.println(linea);
            linea = "Connection con = conectar();" + "\n";
            salida.println(linea);
            linea = "  if (con != null) {" + "\n";
            salida.println(linea);
            linea = " Statement stmt = con.createStatement();" + "\n";
            salida.println(linea);
            linea = " stmt.execute(consulta);" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = "  } catch (Exception e) {" + "\n";
            salida.println(linea);
            linea = "e.printStackTrace();" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            linea = " }" + "\n";
            salida.println(linea);
            salida.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneradorCodigoJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        gestionXMI.GestorXMI gxmi = new GestorXMI();
        try {
            ModeloClase md = gxmi.importarDiagramaClases("C:/Users/Arturo/Documents/pim.xml");
            GestorTransformJava gt = new GestorTransformJava();
            DPaquete dp = gt.transformar(md);
            GeneradorCodigoJava gcj = new GeneradorCodigoJava();
            gcj.generarCapaDatos(dp, "C:/Users/Arturo/Documents/", "dbscript", "root", "root");
        } catch (JDOMException ex) {
            Logger.getLogger(GeneradorCodigoJava.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorCodigoJava.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
