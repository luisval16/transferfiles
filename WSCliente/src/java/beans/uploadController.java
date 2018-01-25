/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.*;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import server.Hello_Service;
import server.IOException_Exception;

/**
 *
 * @author Luis Eduardo Valdez
 */
@Named(value = "uploadController")
@ViewScoped
public class uploadController implements Serializable{

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WSPractice/Hello.wsdl")
    private Hello_Service service;
     
    private Part filePart;

    public Part getFile() {
        return filePart;
    }

    public void setFile(Part file) {
        this.filePart = file;
    }
    
    private File file;
    
    
    
    /**
     * Creates a new instance of uploadController
     */
    public uploadController() {
    }

    
    public void uploadFile() throws IOException, IOException_Exception{
        /*InputStream in = file.getInputStream();
        String filename = file.getSubmittedFileName();*/
        file = new File("C:\\Users\\luis\\Downloads\\cantidadDepto.pdf");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream in = new BufferedInputStream(fis);
        byte[] bytes = new byte[(int) file.length()];
        in.read(bytes);
        uploadFile_1(file.getName(), bytes);
        in.close();
        System.out.println("Exito! archivo subido: " + file.getName());
     
    }

    private boolean uploadFile_1(java.lang.String arg0, byte[] arg1) throws IOException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        server.Hello port = service.getHelloPort();
        return port.uploadFile(arg0, arg1);
    }

    
  
    
    
}
