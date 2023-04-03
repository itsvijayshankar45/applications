package com.subex.bel.esign.serviceimplemenation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.subex.bel.esign.entities.BannerImage;
import com.subex.bel.esign.repo.ImageRepository;
import com.subex.bel.esign.service.ImageService;


@Service
public class ImageServiceImplementation implements ImageService
{   
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public BannerImage saveimage(MultipartFile file) throws Exception 
    {   
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(imageName.contains(".."))
            {
                throw new Exception("Image Name contains invalid path sequence :" + imageName);
            }
            BannerImage bannerImage = new BannerImage(imageName,
            file.getContentType(),
            file.getBytes());

            return imageRepository.save(bannerImage);

        } catch (Exception e) 
        {   
            throw new Exception("could not upload image : " + imageName);
    
        }
       
    }

    @Override
    public BannerImage getImage(String id) throws Exception 
    {
        return imageRepository.findById(id)
        .orElseThrow(() -> new Exception("Image not Found with ID:" + id));

    }
    
}
