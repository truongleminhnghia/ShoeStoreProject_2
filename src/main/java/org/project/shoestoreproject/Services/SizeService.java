package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Product;
import org.project.shoestoreproject.Entity.Size;

import java.util.List;

public interface SizeService {
    Size saverSize(Size size);
    Size getSizeById(int sizeId);
    Size getSizeByValue(int sizeValue);
    List<Size> getSizeByProduct(int productId);
    Size updateSize(int productId, int sizeValue);
    boolean deleteSzie(int productId, int sizeValue);
}
