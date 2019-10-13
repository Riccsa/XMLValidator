package xmlvalidator;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class XMLRepository {

    private File path;
    private List<File> files;

    public XMLRepository(String path) {
        this.path = new File(path);
        try {
            files = Arrays.asList(this.path.listFiles(new XMLFilter()));
        } catch (NullPointerException ex) {
            System.err.println("A megadott elérési út érvénytelen");
            System.exit(0);
        }
        if (files.isEmpty()) {
            System.err.println("A megadott könyvtárban nem található XML fájl");
            System.exit(0);
        }
    }

    public List<File> getFiles() {
        return files;
    }
}
