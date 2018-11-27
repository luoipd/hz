package com.hz.domain.responseBean;

import lombok.Data;

/**
 * @author lyp
 * @date 2018/11/27
 */
@Data
public class HomeParamBean {

    private Integer parentId;
    private Integer moduleId;
    private Integer dataId;
    private Integer moduleType;
    private Integer pModuleId;
    private String title;
    private String content;
    private String vxId;
    private String address;
    private String websits;
    private String phone;
    private String email;
    private Integer industryId;
    private String customerName;
    private String advertisingType;

}
