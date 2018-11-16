package com.hz.domain.responseBean;

import com.hz.domain.*;
import lombok.Data;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/16
 */
@Data
public class ResourceBean {

    MethodResource methodResource;

    AdvertisingStandardDetail advertisingStandardDetail;//标准资源

    AdvertisingUnstandardDetail advertisingUnstandardDetail;//非标资源

    List<AdvertisingStyle> advertisingStyles;//广告样式

    List<HuiBao> huiBaos;//回报组合

}
