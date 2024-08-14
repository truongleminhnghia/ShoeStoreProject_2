package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.DTO.Request.SizeRequest;
import org.project.shoestoreproject.DTO.Respone.SizeRespone;
import org.project.shoestoreproject.Entity.Product;
import org.project.shoestoreproject.Entity.Size;
import org.project.shoestoreproject.Repositories.ProductRepository;
import org.project.shoestoreproject.Repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeServiceImp implements SizeService {

    @Autowired private SizeRepository sizeRepository;
    @Autowired private ProductRepository productRepository;

    @Override
    public Size saveSize(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public Size getSizeById(int sizeId) {
        return sizeRepository.findById(sizeId).orElseThrow(() -> new RuntimeException("Size not found"));
    }

    @Override
    public Size getSizeByValue(int sizeValue) {
        return sizeRepository.findBySizeValue(sizeValue);
    }

    @Override
    public List<Size> getSizeByProduct(int productId) {
        return sizeRepository.findByProduct(productId);
    }

    @Override
    public Size updateSize(int productId, int sizeValue) {
        return null;
    }

    @Override
    public boolean deleteSzie(int productId, int sizeValue) {
        return false;
    }

    @Override
    public void save(Product product, List<Size> sizes) {
        try {
            for (Size size : sizes) {
                Size newSize = new Size();
                newSize.setProduct(product);
                newSize.setSizeValue(size.getSizeValue());
                newSize.setQuantity(size.getQuantity());
                sizeRepository.save(newSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int productId, List<SizeRequest> sizes) {
        try {
            Product productExisting = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            List<Size> sizeByProduct = sizeRepository.findByProduct(productId);
            for (Size size : sizeByProduct) {
                boolean found = false;
                for (SizeRequest sizeRequest : sizes) {
                    if (sizeRequest.getSizeValue() == size.getSizeValue()) {
                        size.setQuantity(sizeRequest.getQuantity());
                        found = true;
                        break; // Exit the inner loop once a match is found
                    }
                }
                if (!found) {
                    sizeRepository.delete(size); // Optionally delete sizes not in the request
                } else {
                    sizeRepository.save(size); // Save the updated size
                }
            }
            for (SizeRequest sizeRequest : sizes) {
                boolean exists = sizeByProduct.stream()
                        .anyMatch(size -> size.getSizeValue() == sizeRequest.getSizeValue());
                if (!exists) {
                    Size newSize = new Size();
                    newSize.setSizeValue(sizeRequest.getSizeValue());
                    newSize.setQuantity(sizeRequest.getQuantity());
                    newSize.setProduct(productExisting);
                    sizeRepository.save(newSize);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SizeRespone> convertToRespone(List<Size> sizes) {
        List<SizeRespone> sizeRespones = new ArrayList<>();
        for (Size size: sizes) {
            SizeRespone sizeRespone = new SizeRespone();
            sizeRespone.setSizeId(size.getSizeID());
            sizeRespone.setSizeValue(size.getSizeValue());
            sizeRespone.setQuantity(size.getQuantity());
            sizeRespones.add(sizeRespone);
        }
        return sizeRespones;
    }
}
