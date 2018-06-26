/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metamodeloDiagramClass;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ELIO
 */
public class Paquete {
    private String name;
    private List<Clase> clases;

    public Paquete() {
        clases=new ArrayList<>();
    }
    public Clase getClase(String nameClase){
        Clase c=null;
        int n=clases.size();
        for (int i = 0; i < n; i++) {
             c=clases.get(i);
            if (c.getName().equals(nameClase)) {
                return c;
            }
            
            
        }
        return c;
    }
    public void addClase(Clase c){
        clases.add(c);
    }
    public Clase getClase(int n){
        return clases.get(n);
    }
    public int getCantidadClases(){
        return clases.size();
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
    public static void main(String[] args) {
        //Un texto cualquiera guardado en una variable
String saludo="Hola";

try
{
//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
File archivo=new File("texto.txt");

//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
FileWriter escribir=new FileWriter(archivo,true);

//Escribimos en el archivo con el metodo write
escribir.write(saludo);

//Cerramos la conexion
escribir.close();
}

//Si existe un problema al escribir cae aqui
catch(Exception e)
{
System.out.println("Error al escribir");
}
}
    
  
    
}
