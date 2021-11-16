package generate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * MTA_CINEMA
 * @author 
 */
@Data
public class MtaCinema implements Serializable {
    private BigDecimal cinemaid;

    private BigDecimal accessorid;

    private String accessorCinemaid;

    private String cinemaName;

    private String cinemaLogo;

    private String cinemaAdd;

    private String contact;

    private String getTicketWay;

    private String cityId;

    private String regionId;

    private Long endBuyDate;

    private Short presaleDay;

    private Date createTime;

    private Date updateTime;

    private String longitude;

    private String latitude;

    private Object location;

    private Short isHavShow;

    private String cityName;

    private String regionName;

    private BigDecimal mergeCityId;

    private BigDecimal mergeRegionId;

    private Short onWork;

    private String cinemaZhuanziCode;

    private String businessCenter;

    private String cinemaHandleFee;

    private String cinemaType;

    private BigDecimal mergeCinemaId;

    private static final long serialVersionUID = 1L;
}