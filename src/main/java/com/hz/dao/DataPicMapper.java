package com.hz.dao;

import com.hz.domain.DataPic;

public interface DataPicMapper {
    int insert(DataPic record);

    int insertSelective(DataPic record);

    void deleteDataPic(DataPic dataPic);
}