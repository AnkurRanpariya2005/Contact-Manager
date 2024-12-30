package com.scm.scm.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String uploadImage(MultipartFile picture);

    public String getUrlFromPublicId(String publicId);
}
