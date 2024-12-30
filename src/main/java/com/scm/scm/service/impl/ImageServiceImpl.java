package com.scm.scm.service.impl;

import java.io.IOException;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.scm.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
    
    @Autowired
    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    String filename = UUID.randomUUID().toString();

    public String uploadImage(MultipartFile picture)  {

        try {

            byte[] data = new byte[picture.getInputStream().available()];
            picture.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id", filename)); 
            return this.getUrlFromPublicId(filename);
           
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        

    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary.url().transformation(new Transformation<>().width(500).height(500)).generate(publicId);
    }

    
}
