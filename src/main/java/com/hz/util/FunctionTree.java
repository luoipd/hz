package com.hz.util;

import lombok.Data;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/12
 */
@Data
public class FunctionTree {

    private String id;
    private String name;
    private String pid;
    private List<FunctionTree> childTree;
    private int level;
}
