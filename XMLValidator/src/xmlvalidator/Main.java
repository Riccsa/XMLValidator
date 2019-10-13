package xmlvalidator;

import java.util.Scanner;

public class Main {

    public static String bekerXML() {
        System.out.println("Kérem adja meg az XML fájlok könyvtárát:");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        return path;
    }

    public static String bekerXSD() {
        System.out.println("Kérem adja meg a schema fájl elérési útját");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        return path;
    }

    public static void main(String[] args) {
        String XMLDir = bekerXML();
        XMLRepository XMLRepo = new XMLRepository(XMLDir);
        String XSDFile = bekerXSD();
        XMLValidator xmlValidator = new XMLValidator(XSDFile);
        xmlValidator.validate(XMLRepo);
    }
}
