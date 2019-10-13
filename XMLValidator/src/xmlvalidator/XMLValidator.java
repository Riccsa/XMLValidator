
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

    public XMLValidator(String path) {
        try {
            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            schemaFile = new StreamSource(new File(path));
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = factory.newSchema(schemaFile);
            validator = schema.newValidator();
            resultList = new ArrayList<>();
        } catch (ParserConfigurationException ex) {
            System.err.println("Parser hiba");
            System.exit(0);
        } catch (SAXException ex) {
            System.err.println("A megadott sémafájl nem található");
            System.exit(0);
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
        String fileName = file.getName();
        String message = "";
        try {
            Document document = parser.parse(file);
            validator.validate(new DOMSource(document));
            message = "érvényes";
        } catch (SAXException ex) {
            message = "érvénytelen, hiba: " + ex.getMessage();
        } catch (IOException ex) {
            System.err.println("A megadott fájl nem található");
        } finally {
            return String.format("%s,   %10s", fileName, message);
        }
    }

    private void export() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        File file = new File(LocalDateTime.now().format(dtf) + ".txt");
        try {
            BufferedWriter bw = new BufferedWriter(new PrintWriter(file));
            for (String result : resultList) {
                bw.write(result);
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException ex) {
            System.err.print("A fájl nem található");
        } catch (IOException ex) {
            System.err.println("Hiba a fájl írása közben");
        }
    }
}
