package com.soap.ws.text;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Tag {
	
	@XmlElement(name = "lang")
	public String language;
	
	@XmlElement(name = "prob")
	public float probability;
	
	
}
