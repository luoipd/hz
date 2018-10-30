package com.hz.service.impl;

import com.hz.dao.UserMapper;
import com.hz.service.CityService;
import com.hz.dao.CityDao;
import com.hz.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;
    @Autowired
    private UserMapper userMapper;

    public City findCityByName(String cityName) {
        return cityDao.findByName(cityName);
    }

}
