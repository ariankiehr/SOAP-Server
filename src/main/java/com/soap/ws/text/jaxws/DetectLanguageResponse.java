
package com.soap.ws.text.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "detectLanguageResponse", namespace = "http://text.ws.soap.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detectLanguageResponse", namespace = "http://text.ws.soap.com/")
public class DetectLanguageResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.soap.ws.text.Tag> _return;

    /**
     * 
     * @return
     *     returns List<Tag>
     */
    public List<com.soap.ws.text.Tag> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.soap.ws.text.Tag> _return) {
        this._return = _return;
    }

}
