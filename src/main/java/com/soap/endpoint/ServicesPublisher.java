package com.soap.endpoint;

import javax.xml.ws.Endpoint;

import com.soap.ws.hash.Hash;
import com.soap.ws.mail.Mail;
import com.soap.ws.text.Text;

public class ServicesPublisher {

	public static void main(String[] args) {
		
		Endpoint.publish("http://localhost:9999/ws/Hash", new Hash());
		Endpoint.publish("http://localhost:9999/ws/Mail", new Mail());
		Endpoint.publish("http://localhost:9999/ws/Text", new Text());
    }
 
}
