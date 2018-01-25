/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Luis Eduardo Valdez
 */
public class WebServiceServer {
    public static void main(String[] args) {
       
        String bindingURI = "http://localhost:8080/WSPractice/Hello";
        Hello service = new Hello();
        Endpoint.publish(bindingURI, service);
        System.out.println("Server started at: " + bindingURI);
    }
}
