package com.subex.bel.esign.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.subex.bel.esign.entities.BannerImage;

@Service
public interface ImageService {

    BannerImage saveimage(MultipartFile file) throws Exception;

    BannerImage getImage(String id) throws Exception;
    
}
