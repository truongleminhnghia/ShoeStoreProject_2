package org.project.shoestoreproject.services;

import org.project.shoestoreproject.dto.requests.SizeRequest;
import org.project.shoestoreproject.dto.respones.SizeRespone;
import org.project.shoestoreproject.entities.Product;
import org.project.shoestoreproject.entities.Size;

import java.util.List;

public interface SizeService {
    Size saveSize(Size size);
    Size getSizeById(int sizeId);
    Size getSizeByValue(int sizeValue);
    List<Size> getSizeByProduct(int productId);
    Size updateSize(int productId, int sizeValue);
    boolean deleteSzie(int productId, int sizeValue);
    public void save(Product product, List<Size> sizes);
    public void update(int productId, List<SizeRequest> sizes);
    List<SizeRespone> convertToRespone(List<Size> size);
}
