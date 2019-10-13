
package xmlvalidator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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


public class XMLValidator {

    private Source schemaFile;
    private Schema schema;
    private DocumentBuilder parser;
    private Validator validator;
    private XMLRepository XMLRepo;
    private List<String> resultList;
    private File resultFile;

    public XMLValidator(String path) {

        try {
            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            schemaFile = new StreamSource(new File(path));
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = factory.newSchema(schemaFile);
            validator = schema.newValidator();
            resultList = new ArrayList<>();
        } catch (ParserConfigurationException ex) {
            System.out.println("Parser hiba");
        } catch (SAXException ex) {
            System.out.println("A megadott sémafájl érvénytelen");
        }

    }

    public void validate(XMLRepository XMLRepo) {

        for (File file : XMLRepo.getFiles()) {

            String result = check(file);
            resultList.add(result);
        }

        export();

    }

    private String check(File file) {
        
        String date;
        String fileName;
        String result;


        try {
            Document document = parser.parse(file);
            validator.validate(new DOMSource(document));
            
            date=LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
            fileName=file.getName();
            result="érvényes";
            
            return String.format("%5s,%-20s,%10s",date,fileName,result);

            //return LocalDateTime.now().toString() + "\t" + file.getName() + "   " + "érvényes;";

        } catch (SAXException ex) {
            
            date=LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
            fileName=file.getName();
            result="érvénytelen, hiba: "+ex.getMessage();
                    
           return String.format("%10s,%-20s,%10s",date,fileName,result);
            //return LocalDateTime.now().toString() + "\t" + file.getName() + "   " + "érvénytelen, hiba: " + ex.getMessage();
        } catch (IOException ex) {
            System.out.println("A megadott fájl nem található");
        }

        return "Ellenőrzési hiba";

    }

    public void export() {

        File file = new File("result.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new PrintWriter(file));
            for (String result : resultList) {
                bw.write(result);
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException ex) {
            System.out.print("A fájl nem található");
        } catch (IOException ex) {
            System.out.println("Hiba a fájl írása közben");
        }

    }

}
