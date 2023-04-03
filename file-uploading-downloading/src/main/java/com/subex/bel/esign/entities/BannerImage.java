package com.subex.bel.esign.entities;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="banner_image")
public class BannerImage 
{   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;

    @Column(name="image_name")
    private String imageName;

    @Column(name="image_type")
    private String imageType;

    @Lob
    private byte[] data;

    public BannerImage(String imageName, String imageType, byte[] data) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.data = data;
    }

    
}
