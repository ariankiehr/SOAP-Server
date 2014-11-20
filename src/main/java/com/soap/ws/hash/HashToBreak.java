package com.soap.ws.hash;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HashToBreak {
	
	@XmlElement(name = "hash")
	public String hash;
	
	@XmlElement(name = "type")
	public String hashType;
	
	@XmlElement(name = "timeout")
	public Integer timeout;

}
