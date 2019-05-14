/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class LibraryProject {

   
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        
        File dosya_kitaplik=new File("C:\\Users\\Fe\\Desktop\\kitap.txt");
           
        ArrayList<String> kitaplar=new ArrayList<String>();
        
        
        new Kitaplik(dosya_kitaplik,kitaplar);
       
        
    }
    
}
