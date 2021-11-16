package com.cloud.module.employee.http;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author 23141
 */
@Data
@XmlRootElement(name = "QueryCinemaList", namespace = "http://soap.ws.tsp.oristartech.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class DadiCinemaParam  {
    @XmlElement(name = "pAppCode")
    private String pAppCode;
    @XmlElement(name = "pCompress")
    private String pCompress;
    @XmlElement(name = "pVerifyInfo")
    private String pVerifyInfo;
}
