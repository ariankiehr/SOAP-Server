package com.soap.ws.hash;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "EnumType")
@XmlEnum
public enum EnumType {

    MD5, SHA1

}