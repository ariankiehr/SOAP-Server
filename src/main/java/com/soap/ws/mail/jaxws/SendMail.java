
package com.soap.ws.mail.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "sendMail", namespace = "http://mail.ws.soap.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendMail", namespace = "http://mail.ws.soap.com/")
public class SendMail {

    @XmlElement(name = "arg0", namespace = "")
    private com.soap.ws.mail.SendMailInfo arg0;

    /**
     * 
     * @return
     *     returns SendMailInfo
     */
    public com.soap.ws.mail.SendMailInfo getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.soap.ws.mail.SendMailInfo arg0) {
        this.arg0 = arg0;
    }

}
