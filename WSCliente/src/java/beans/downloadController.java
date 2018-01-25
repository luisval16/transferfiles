/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.xml.ws.WebServiceRef;
import server.Hello_Service;
import server.IOException_Exception;

/**
 *
 * @author Luis Eduardo Valdez
 */
@Named(value = "downloadController")
@ViewScoped
public class downloadController implements Serializable{

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WSPractice/Hello.wsdl")
    private Hello_Service service;
    
    String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    /**
     * Creates a new instance of downloadController
     */
    public downloadController() {
    }
    
    public void descargar() throws IOException_Exception, IOException{
    byte[] fileBytes = downloadFile(filename);
    String destination = getRoot() + createFolder(getRoot()) + File.separator + filename;
        try {
            FileOutputStream fos = new FileOutputStream(destination);
            BufferedOutputStream out = new BufferedOutputStream(fos);
            out.write(fileBytes);
            out.close();
            System.out.println("File Downloaded! " + filename);
        } catch (Exception e) {
            System.err.println(e);
        }
    
    }
    
    

    private byte[] downloadFile(java.lang.String arg0) throws IOException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        server.Hello port = service.getHelloPort();
        return port.downloadFile(arg0);
    }
    
    private String getRoot(){
         String osName = System.getProperty("os.name");
        String rootDir = "";
        if (osName.toLowerCase().contains("window")) {
            //System.out.println("Windows");
            rootDir = System.getenv("SystemDrive") + "\\";
        }else{
            //System.out.println("Otro");
            rootDir ="/";
        }
        return rootDir;
        
        
    }
    
    
    private String createFolder(String path) throws IOException{
    String directoryName = "pdfDescargas";
    File directory = new File(path+directoryName);
    directory.mkdir();
    return directoryName;
    }
    
}
