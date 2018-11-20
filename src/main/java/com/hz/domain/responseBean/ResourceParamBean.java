package com.hz.domain.responseBean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lyp
 * @date 2018/11/19
 */
@Data
public class ResourceParamBean {

    private Integer methodSourceId;

    private String name;

    private Integer methodType;

    private Integer mediaId;

    private BigDecimal price;

    private String lightExposure;

    private String pv;

    private String importLevel;

    private String desc;

    private String characteristic;

    private Integer standardId;

    private String platform;

    private String chargeMode;

    private Integer unstandardId;

    private String xiwei;

    private String scheduling;

    private String detail;

    private String isSplit;


}
