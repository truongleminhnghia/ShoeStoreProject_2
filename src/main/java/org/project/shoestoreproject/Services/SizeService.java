package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.DTO.Request.SizeRequest;
import org.project.shoestoreproject.DTO.Respone.SizeRespone;
import org.project.shoestoreproject.Entity.Product;
import org.project.shoestoreproject.Entity.Size;

import javax.sound.sampled.Port;
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
