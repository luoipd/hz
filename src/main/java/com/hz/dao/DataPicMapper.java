package com.hz.dao;

import com.hz.domain.DataPic;

import java.util.List;

public interface DataPicMapper {
    int insert(DataPic record);

    int insertSelective(DataPic record);

    void deleteDataPic(DataPic dataPic);

    List<DataPic> selectDataPicList(int moduleId,int dataId);



}