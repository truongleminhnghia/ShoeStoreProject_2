package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Image;

public interface ImageService {
    Image saveImage(String imageUrl, int productId);
    Image getIamgeById(int imageId);
    Image getImageByProduct(int productId);
}
