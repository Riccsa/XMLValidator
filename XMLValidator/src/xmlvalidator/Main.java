/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlvalidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class Main {
    
   
       public static String bekerXML() {
           System.out.println("Kérem adja meg az XML fájlok könyvtárát:");
           System.out.println();
           Scanner sc=new Scanner(System.in);
           String path=sc.nextLine();
           
           return path;       
       
       }
       
        public static String bekerXSD() {
           System.out.println("Kérem adja meg a schema fájl elérési útját");
           Scanner sc=new Scanner(System.in);
           String path=sc.nextLine();
           
           return path;
        
           
        }

    public static void main(String[] args) {
        
        String XMLDir=bekerXML();
        XMLRepository XMLRepo=new XMLRepository(XMLDir);
        
        String XSDFile=bekerXSD();
        XMLValidator xmlValidator=new XMLValidator(XSDFile);
        
        xmlValidator.validate(XMLRepo);
       
        
    
     
      
      
    }
    
}