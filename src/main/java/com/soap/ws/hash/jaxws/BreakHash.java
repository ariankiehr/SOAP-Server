
package com.soap.ws.hash.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "breakHash", namespace = "http://hash.ws.soap.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "breakHash", namespace = "http://hash.ws.soap.com/")
public class BreakHash {

    @XmlElement(name = "arg0", namespace = "")
    private com.soap.ws.hash.HashToBreak arg0;

    /**
     * 
     * @return
     *     returns HashToBreak
     */
    public com.soap.ws.hash.HashToBreak getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.soap.ws.hash.HashToBreak arg0) {
        this.arg0 = arg0;
    }

}
