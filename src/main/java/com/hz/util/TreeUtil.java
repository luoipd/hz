package com.hz.util;

/**
 * @author lyp
 * @date 2018/11/12
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TreeUtil {

    private List<FunctionTree> rootList; //根节点对象存放到这里

    private List<FunctionTree> bodyList; //其他节点存放到这里，可以包含根节点

    public TreeUtil(List<FunctionTree> rootList, List<FunctionTree> bodyList) {
        this.rootList = rootList;
        this.bodyList = bodyList;
    }

    public List<FunctionTree> getTree(){   //调用的方法入口
        if(bodyList != null && !bodyList.isEmpty()){
            //声明一个map，用来过滤已操作过的数据
            Map<String,String> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(beanTree -> getChild(beanTree,map));
            return rootList;
        }
        return null;
    }

    public void getChild(FunctionTree beanTree,Map<String,String> map){
        List<FunctionTree> childList = Lists.newArrayList();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getId()))
                .filter(c ->c.getPid().equals(beanTree.getId()))
                .forEach(c ->{
                    map.put(c.getId(),c.getPid());
                    getChild(c,map);
                    childList.add(c);
                });
        beanTree.setChildTree(childList);
    }

    public static void main(String[] args){
        FunctionTree beanTree1 = new FunctionTree();
        beanTree1.setId("540000");
        beanTree1.setName("西藏省");
        beanTree1.setPid("100000"); //最高节点
        FunctionTree beanTree2 = new FunctionTree();
        beanTree2.setId("540100");
        beanTree2.setName("拉萨市");
        beanTree2.setPid("540000");
        FunctionTree beanTree3 = new FunctionTree();
        beanTree3.setId("540300");
        beanTree3.setName("昌都市");
        beanTree3.setPid("540000");
        FunctionTree beanTree4 = new FunctionTree();
        beanTree4.setId("540121");
        beanTree4.setName("林周县");
        beanTree4.setPid("540100");
        FunctionTree beanTree5 = new FunctionTree();
        beanTree5.setId("540121206");
        beanTree5.setName("阿朗乡");
        beanTree5.setPid("540121");
        FunctionTree beanTree6 = new FunctionTree();
        List<FunctionTree> rootList = new ArrayList<FunctionTree>();
        rootList.add(beanTree1);
        List<FunctionTree> bodyList = new ArrayList<FunctionTree>();
        bodyList.add(beanTree1);
        bodyList.add(beanTree2);
        bodyList.add(beanTree3);
        bodyList.add(beanTree4);
        bodyList.add(beanTree5);
        TreeUtil utils =  new TreeUtil(rootList,bodyList);
        List<FunctionTree> result =  utils.getTree();
        result.get(0);
    }
}