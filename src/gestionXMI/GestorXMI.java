/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionXMI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metamodeloDiagramClass.*;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author ELIO
 */
public class GestorXMI {
    private PrimitiveDataType getType(List<PrimitiveDataType> types,String name){
        PrimitiveDataType primitive=null;
        int n=types.size();
        for (int i = 0; i < n; i++) {
            primitive=types.get(i);
            if (primitive.getName().equals(name)) {
                return primitive;
            }
        }
        return primitive;
    }
    public ModeloClase importarDiagramaClases(String path) throws JDOMException, IOException{
            SAXBuilder builder=new SAXBuilder(false);
            Document doc;
            ModeloClase md=new ModeloClase();
            Paquete p=null;
            Clase c=null;
            doc = builder.build(path);
            org.jdom.Element raiz= doc.getRootElement();
            
            Element extension=(Element) raiz.getChildren().get(2);
            List<PrimitiveDataType> typesDatos=obtenerTiposDatosPrim(extension);
            md.setTypesData(typesDatos);
            Element elemento=(Element) extension.getChildren().get(0);
            List<Element> hijosElemento=elemento.getChildren();
            int n=hijosElemento.size();
            for (int i = 0; i < n; i++) {
                Element hijo=(Element) hijosElemento.get(i);
               Attribute at=(Attribute) hijo.getAttributes().get(1);
               Attribute att=(Attribute) hijo.getAttributes().get(2);
               if(at!=null){
                   if (at.getName().equals("type")) {
                       if (at.getValue().equals("uml:Class")) {
                           Element nieto2=(Element) hijo.getChildren().get(1);
                           if (nieto2!=null) {
                               Attribute a2=nieto2.getAttribute("stereotype");
                               if (a2!=null) {
                                   if (a2.getValue().equals("persistente")) {
                                       
                                       if (md.getPaquete()!=null) {
                                           if (att!=null&&att.getName().equals("name")) {
                                               
                                               c=new Clase();
                                               c.setName(att.getValue());
                                               c.setIspersistente(true);
                                               p.addClase(c);
                                               
                                               Element nieto=(Element) hijo.getChildren().get(9);
                                               if (nieto!=null) {
                                                    List<Element> bisnietos=nieto.getChildren();
                                                    int m2=bisnietos.size();
                    
                                                     for (int j = 0; j < m2; j++) {
                                                         Element bisnieto=(Element) nieto.getChildren().get(j);
                                                         Atribute a=new Atribute();
                                                         a.setName(bisnieto.getAttribute("name").getValue());
                                                         Element bisbisNieto = (Element) bisnieto.getChildren().get(3);
                                                         a.setType(getType(typesDatos,bisbisNieto.getAttribute("type").getValue()));
                                                         c.addAtributo(a);
                                                    }    
                                               }
                                                             
                                               
                                               
                                           }
                                           
                                           
                                       }
                                   }
                                   
                               }
                               
                           }
                       }else{
                           if (at.getValue().equals("uml:Package")) {
                               if (att!=null&&att.getName().equals("name")) {
                                               
                                     p=new Paquete();
                                     p.setName(att.getValue());
                                     md.setPaquete(p);          
                               }
                               
                            
                           }
                       }
                   }
               }
              
            }
            
           Element conectores=(Element) extension.getChildren().get(1);
           List<Element> hijosConectores=conectores.getChildren();
           int canthijos=hijosConectores.size();
            Asociacion a=null;
           for (int i = 0; i < canthijos; i++) {
               
            
            Element hijoConectores=hijosConectores.get(i);
            Element hijoSource=hijoConectores.getChild("source");
            Element nietoSource=hijoSource.getChild("model");
            String claseFuente=nietoSource.getAttributeValue("name");
            Clase cf=md.getPaquete().getClase(claseFuente);     
            
            Element hijotarget=hijoConectores.getChild("target");
            Element nietotarget=hijotarget.getChild("model");
            String claseDestino=nietotarget.getAttributeValue("name");
            Clase cd=md.getPaquete().getClase(claseDestino);
               if (cf!=null&&cd!=null) {
                   a=new Asociacion(); 
                   a.setOrigen(cf);
                   a.setDestino(cd);
                   Element hijoType=hijoConectores.getChild("properties");
                   a.setNombre(hijoType.getAttributeValue("ea_type"));
                   Element nietoSource2=hijoSource.getChild("type"); 
                   String multFuente=nietoSource2.getAttributeValue("multiplicity");      
                        if (multFuente!=null) {
                            a.setMultiplicidadOrigen(multFuente);
                         }else{
                            a.setMultiplicidadOrigen("");
                         }
                   Element nietotarget2=hijotarget.getChild("type");
                   String multDestino=nietotarget2.getAttributeValue("multiplicity");      
                        if (multDestino!=null) {
                            a.setMultipliciadaDestino(multDestino);
                        }else{
                            a.setMultipliciadaDestino("");
                        }  
                  md.getAsociaciones().add(a);      
               }
            
            
           
            }
            
            
            return md;
            
           
    }
   
    
    private List<PrimitiveDataType> obtenerTiposDatosPrim(Element e){
        Element elemento=(Element) e.getChildren().get(2);
        
        
            Element hijo=(Element) elemento.getChildren().get(0);
            Element nieto=(Element) hijo.getChildren().get(0);
            
            int n=nieto.getChildren().size();
            List bisneitos=nieto.getChildren();
            List<PrimitiveDataType> typeDatos=new ArrayList<>();
            PrimitiveDataType pt=null;       
            for (int i = 0; i < n; i++) {
            Element bisneito=(Element)bisneitos.get(i);
                pt=new PrimitiveDataType();
                pt.setName(bisneito.getAttributeValue("name"));
                typeDatos.add(pt);
                //System.out.println(bisneito.getName());
            }
        
        
        return typeDatos;
    }
    
    public static void main(String[] args) {
        GestorXMI gxmi=new GestorXMI();
        try {
            ModeloClase md= gxmi.importarDiagramaClases("D:/cc/pim.xml");
            Paquete p=md.getPaquete();
            System.out.println(p.getName()+"  :paquete name");
            int n=p.getCantidadClases();
            
            for (int i = 0; i < n; i++) {
                Clase c=p.getClase(i);
                System.out.println(c.getName()+" clase name");
                List<Atribute> atributos=c.getAtributos();
                int m=atributos.size();
                for (int j = 0; j < m; j++) {
                    Atribute a=atributos.get(j);
                    System.out.println(a.getName()+" :"+ a.getType().getName()+" :" );
                    
                }
                
            }
            /*List<Asociacion> asociones=md.getAsociaciones();
            int m=asociones.size();
            for (int i = 0; i < m; i++) {
                System.out.println("");
            }*/
        } catch (JDOMException | IOException ex) {
            System.out.println(ex);
        }
    }
    
}
