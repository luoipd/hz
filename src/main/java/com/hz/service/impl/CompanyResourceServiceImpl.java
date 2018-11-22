package com.hz.service.impl;

import com.github.pagehelper.PageHelper;
import com.hz.dao.DataPicMapper;
import com.hz.dao.HomeMapper;
import com.hz.dao.PictureVideoMapper;
import com.hz.domain.DataPic;
import com.hz.domain.Home;
import com.hz.domain.PictureVideo;
import com.hz.domain.User;
import com.hz.service.CompanyResourceService;
import com.hz.util.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/21
 */
@Service
public class CompanyResourceServiceImpl implements CompanyResourceService {

    @Autowired
    HomeMapper homeMapper;
    @Autowired
    DataPicMapper dataPicMapper;
    @Autowired
    PictureVideoMapper pictureVideoMapper;

    @Override
    public List<Home> selectHomeList(Home home, PageRequest pageRequest) {
        home.setStatus(1);
        home.setProposalId(0);
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Home> homes = homeMapper.selectHomeList(home);
        for(Home home1:homes){
            List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(home1.getModuleId(),home1.getId());
            home1.setPictureVideos(pictureVideos);
        }
        return homes;
    }

    @Override
    public int countHomeList(Home home) {
        home.setStatus(1);
        home.setProposalId(0);
        return homeMapper.countHomeList(home);
    }

    @Override
    public int createHome(Home home, List<Integer> picIds, User user) {
        home.setStatus(1);
        homeMapper.insertSelective(home);
        insertDataPic(picIds,home,user);
        return home.getId();
    }

    @Override
    public void updateHome(Home home, List<Integer> picIds, User user) {
        homeMapper.updateByPrimaryKeySelective(home);
        insertDataPic(picIds,home,user);

    }

    @Override
    public void deleteDataPic(DataPic dataPic) {
        dataPicMapper.deleteDataPic(dataPic);
    }

    @Override
    public Home getHomeInfo(int id) {
        Home home = homeMapper.selectByPrimaryKey(id);
        List<PictureVideo> pictureVideos = pictureVideoMapper.selectPicVideoByModuleAndDataId(home.getModuleId(),home.getId());
        home.setPictureVideos(pictureVideos);
        return home;
    }

    public void insertDataPic(List<Integer> picIds,Home home,User user){
        for(int i :picIds){
            DataPic dataPic = new DataPic();
            dataPic.setDataId(home.getId());
            dataPic.setPicId(i);
            dataPic.setModuleId(home.getModuleId());
            dataPic.setUpdaterId(user.getId());
            dataPic.setCreaterId(user.getId());
            dataPicMapper.insertSelective(dataPic);
        }
    }

}
