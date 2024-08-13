package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Image;
import org.project.shoestoreproject.Repositories.ImageRepository;
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
