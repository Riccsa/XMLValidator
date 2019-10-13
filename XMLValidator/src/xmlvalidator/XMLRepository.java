/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlvalidator;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author richard.bagi
 */
public class XMLRepository {
    
    private File path;
    private List<File> files;
    
    public XMLRepository(String path) {
        
        this.path=new File(path);
        
        files=Arrays.asList(this.path.listFiles(new XMLFilter()));

    }

    public List<File> getFiles() {
        return files;
    }
    
}
