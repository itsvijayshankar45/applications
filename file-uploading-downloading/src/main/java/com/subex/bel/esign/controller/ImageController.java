package com.subex.bel.esign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.subex.bel.esign.ResponseData;
import com.subex.bel.esign.entities.BannerImage;
import com.subex.bel.esign.service.ImageService;

@RestController
@RequestMapping("/")
public class ImageController 
{
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseData uploadimage(@RequestParam("image")MultipartFile file) throws Exception
    {   
        BannerImage bannerImage = null;
        String downloadURl ="";
        bannerImage = imageService.saveimage(file);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/download/")
        .path(bannerImage.getId())
        .toUriString();

    return new ResponseData(bannerImage.getImageName(),
        downloadURl,
        file.getContentType(),
        file.getSize());
    }
    
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> getBannerImage(@PathVariable(value="fileId") String id) throws Exception
    {
        BannerImage bannerImage = null;
        bannerImage =imageService.getImage(id);
        
        return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(bannerImage.getImageType()))
        .header(HttpHeaders.CONTENT_DISPOSITION,
        "BannerImage ; Iamgename=\"" + bannerImage.getImageName() +"\"")
        .body(new ByteArrayResource(bannerImage.getData()));

    }

}
    

