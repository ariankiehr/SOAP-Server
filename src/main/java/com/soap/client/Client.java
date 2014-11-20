package com.soap.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.soap.ws.text.IText;

public class Client {

	public static void main(String[] args) throws Exception {
		 
		URL url = new URL("http://localhost:9999/ws/Text?wsdl");
 
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://text.ws.soap.com/", "TextService");
 
        Service service = Service.create(url, qname);
 
        IText text = service.getPort(IText.class);
 
   
        System.out.println( text.detectLanguage( "sad" ).get(0).language  );
 
    }
 
	
}
