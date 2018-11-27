package com.hz.domain.responseBean;

import lombok.Data;

/**
 * @author lyp
 * @date 2018/11/27
 */
@Data
public class MarketParamBean {

    private Integer parentId;
    private Integer moduleId;
    private Integer dataId;
    private Integer moduleType;
    private Integer pModuleId;
    private String title;
    private String content;
    private Integer industryId;
}
