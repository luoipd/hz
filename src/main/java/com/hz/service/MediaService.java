package com.hz.service;

import com.hz.domain.Media;
import com.hz.util.page.PageRequest;

import java.util.List;

/**
 * @author lyp
 * @date 2018/11/14
 */
public interface MediaService {


    List<Media> getMediaList(Media media, PageRequest pageRequest);

    List<Media> getMediaList();

    int countMedia(Media media);

    void insertMedia(Media media) throws Exception;

    void editMedia(Media media) throws Exception;

    void delMediaById(int id) throws Exception;

    Media getMediaInfo(int id);


}
