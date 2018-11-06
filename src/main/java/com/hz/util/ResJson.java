package com.hz.util;

import lombok.Data;

/**
 * @author lyp
 * @date 2018/11/5
 */
@Data
public class ResJson {


    int status = 1;

    String desc = "success";

    Object data;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
