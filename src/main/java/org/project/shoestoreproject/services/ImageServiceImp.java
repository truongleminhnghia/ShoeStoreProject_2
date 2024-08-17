package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Image;
import org.project.shoestoreproject.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImp implements ImageService{

    @Autowired private ImageRepository imageRepository;

    @Override
    public Image saveImage(String imageUrl, int productId) {
        return null;
    }

    @Override
    public Image getIamgeById(int imageId) {
        return null;
    }

    @Override
    public Image getImageByProduct(int productId) {
        return null;
    }
}
