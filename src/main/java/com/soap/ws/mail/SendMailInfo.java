package com.soap.ws.mail;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SendMailInfo {
	
	@XmlElement(name = "from")
	public String from;
	
	@XmlElement(name = "pass")
	public String pass;
	
	@XmlElement(name = "to")
	public List<String> to;
	
	@XmlElement(name = "subject")
	public String subject;
	
	@XmlElement(name = "body")
	public String body;

}
