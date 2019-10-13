package xmlvalidator;

import java.io.File;
import java.io.FileFilter;

public class XMLFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return pathname.getAbsolutePath().toLowerCase().endsWith(".xml");
    }
}
