package org.project.shoestoreproject.services;

import org.project.shoestoreproject.dto.requests.SizeRequest;
import org.project.shoestoreproject.dto.respones.SizeRespone;
import org.project.shoestoreproject.entities.Product;
import org.project.shoestoreproject.entities.Size;
import org.project.shoestoreproject.repositories.ProductRepository;
import org.project.shoestoreproject.repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
        // Find existing product or throw an exception
        Product productExisting = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        // Fetch current sizes from the database for the product
        List<Size> sizeByProduct = sizeRepository.findByProduct(productId);
        // Create maps for efficient lookup
        Map<Integer, Size> sizeByValueMap = sizeByProduct.stream()
                .collect(Collectors.toMap(Size::getSizeValue, size -> size));
        // Iterate through the sizes from the request and update existing sizes
        for (SizeRequest sizeRequest : sizes) {
            Size existingSize = sizeByValueMap.get(sizeRequest.getSizeValue());
            if (existingSize != null) {
                existingSize.setQuantity(existingSize.getQuantity() + sizeRequest.getQuantity());
            } else {
                // Create new size if it doesn't exist
                Size newSize = new Size();
                newSize.setSizeValue(sizeRequest.getSizeValue());
                newSize.setQuantity(sizeRequest.getQuantity());
                newSize.setProduct(productExisting);
                sizeByProduct.add(newSize); // Add to the list
            }
        }
        // Delete sizes that are not in the request list
        Set<Integer> sizeValuesFromRequest = sizes.stream()
                .map(SizeRequest::getSizeValue)
                .collect(Collectors.toSet());
        sizeByProduct.removeIf(size -> !sizeValuesFromRequest.contains(size.getSizeValue()));
        // Save all changes in bulk
        sizeRepository.saveAll(sizeByProduct);
    }

    @Override
    public List<SizeRespone> convertToRespone(List<Size> sizes) {
        List<SizeRespone> sizeRespones = new ArrayList<>();
        for (Size size: sizes) {
            SizeRespone sizeRespone = new SizeRespone();
            sizeRespone.setSizeId(size.getSizeId());
            sizeRespone.setSizeValue(size.getSizeValue());
            sizeRespone.setQuantity(size.getQuantity());
            sizeRespones.add(sizeRespone);
        }
        return sizeRespones;
    }
}
