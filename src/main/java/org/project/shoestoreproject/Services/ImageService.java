package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Image;

public interface ImageService {
    Image saveImage(String imageUrl, int productId);
    Image getIamgeById(int imageId);
    Image getImageByProduct(int productId);
}
