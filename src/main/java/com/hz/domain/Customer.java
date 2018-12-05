package com.hz.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lyp
 * @date 2018/12/4
 */
@Data
public class Customer {

    private Integer id;
    private String customerName;
    private Integer picId;
    private String mobile;
    private String email;
    private String company;
    private String content;
    private String desc;
    private String status;
    private Integer createrId;
    private Date createTime;
    private Integer updaterId;
    private Date updateTime;
    private List<Integer> createrIds;
}
