/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author Luis Eduardo Valdez
 */
@WebService(serviceName = "Hello")
public class Hello {

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    /*@WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }*/
    
    @WebMethod
    public String sayHello(String name)
    {
    
    return "Hello " +name + " !"; 
    }
    
    @WebMethod
    public boolean uploadFile(String filename,byte[] bytes) throws IOException{
    boolean flag = false;
    String destination = getRoot() + createFolder(getRoot()) + File.separator + filename;
    FileOutputStream fos= new FileOutputStream(destination);
    BufferedOutputStream outputStream= new BufferedOutputStream(fos);
    outputStream.write(bytes);
    outputStream.close();
   
       
        
        System.out.println("Archivo Creado! en: " + destination);
        flag = true;
    return flag;
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
    String directoryName = "pdf";
    File directory = new File(path+directoryName);
    directory.mkdir();
    return directoryName;
    }
    
    @WebMethod
    public byte[] downloadFile(String filename) throws IOException{
    String destination = getRoot() + createFolder(getRoot()) + File.separator + filename;
        try {
            File file = new File(destination);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream in =  new BufferedInputStream(fis);
            byte[] fileBytes = new byte[(int) file.length()];
            in.read(fileBytes);
            in.close();
            return fileBytes;
        } catch (IOException e) {
            System.err.println(e);
            throw new WebServiceException(e);
        }
        
    
    }
}
