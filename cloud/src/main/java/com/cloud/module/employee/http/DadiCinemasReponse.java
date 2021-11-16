package com.cloud.module.employee.http;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "QueryCinemaListResponse", namespace = "http://soap.ws.tsp.oristartech.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class DadiCinemasReponse {

    @XmlElement(name = "return")
    private String rtn;

    @Data
    @XmlRootElement(name = "QueryCinemaListResult")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class QueryCinemaListResult {
        @XmlElement(name = "ResultCode")
        private String ResultCode;
        @XmlElement(name = "Message")
        private String Message;
        @XmlElement(name = "Cinemas")
        private Cinemas Cinemas;
    }

    @Data
    @XmlRootElement(name = "Cinemas")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Cinemas {
        @XmlElement(name = "Cinema")
        List<Cinema> cinemas;

    }

    @Data
    @XmlRootElement(name = "Cinema")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Cinema {
        @XmlElement(name = "CinemaCode")
        private String CinemaCode;
        @XmlElement(name = "CinemaName")
        private String CinemaName;
        @XmlElement(name = "Province")
        private String Province;
        @XmlElement(name = "City")
        private String City;
        @XmlElement(name = "CreateDate")
        private String CreateDate;
        @XmlElement(name = "PriceType")
        private String PriceType;
    }
}
