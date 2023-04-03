package com.subex.bel.esign.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subex.bel.esign.entities.BannerImage;

@Repository
public interface ImageRepository extends JpaRepository<BannerImage,String> {
    
}
