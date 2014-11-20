package com.soap.ws.hash;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class HashToBreak {
	
	@XmlElement(name = "hash", required = true)
	public String hash;
	
	@XmlElement(name = "type", required = true)
	public EnumType type; 
	
	@XmlElement(name = "timeout", required = true)
	public Integer timeout;

}