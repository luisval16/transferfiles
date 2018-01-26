/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejavaapp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import server.IOException_Exception;

/**
 *
 * @author Luis Eduardo Valdez
 */
public class ClienteJavaApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, IOException_Exception {
        // TODO code application logic here

        //Crea si no existey obtiene el destino de la bandeja de subida
        String destination = getRoot() + createFolder(getRoot());

        File file = new File("C:\\Users\\luis\\Downloads");
        File archivo;
        File[] listFiles = file.listFiles();
        FileInputStream fis;
        BufferedInputStream  in;
        //destination += File.separator;
        int y = 0;
        if (listFiles.length != 0) {
            for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].getName().contains(".pdf")) {
                archivo = new File(file.getAbsolutePath() +File.separator+ listFiles[i].getName());
                fis = new FileInputStream(archivo);
                in = new BufferedInputStream(fis);
                byte[] bytes = new byte[(int) archivo.length()];
                in.read(bytes);
                uploadFile(archivo.getName(), bytes);
                y++;
                System.out.println("Archivo "+y+": "+archivo.getName());
                //in.close();
            }
        }
        }else{
            System.out.println("No hay archivos!");
        }

    }

    private static boolean uploadFile(java.lang.String arg0, byte[] arg1) throws IOException_Exception {
        server.Hello_Service service = new server.Hello_Service();
        server.Hello port = service.getHelloPort();
        return port.uploadFile(arg0, arg1);
    }

    private static byte[] downloadFile(java.lang.String arg0) throws IOException_Exception {
        server.Hello_Service service = new server.Hello_Service();
        server.Hello port = service.getHelloPort();
        return port.downloadFile(arg0);
    }

    private static String getRoot() {
        String osName = System.getProperty("os.name");
        String rootDir = "";
        if (osName.toLowerCase().contains("window")) {
            //System.out.println("Windows");
            rootDir = System.getenv("SystemDrive") + "\\";
        } else {
            //System.out.println("Otro");
            rootDir = "/";
        }
        return rootDir;

    }

    private static String createFolder(String path) throws IOException {
        String directoryName = "bandejaPDF";
        File directory = new File(path + directoryName);
        directory.mkdir();
        return directoryName;
    }
}
