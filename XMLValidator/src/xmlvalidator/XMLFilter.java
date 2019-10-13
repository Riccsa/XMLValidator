/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlvalidator;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author richard.bagi
 */
public class XMLFilter implements FileFilter{

    @Override
    public boolean accept(File pathname) {
         return pathname.getAbsolutePath().toLowerCase().endsWith(".xml");
    }
    
}
