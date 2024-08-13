package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Product;
import org.project.shoestoreproject.Entity.Size;
import org.project.shoestoreproject.Repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImp implements SizeService {

    @Autowired private SizeRepository sizeRepository;

    @Override
    public Size saverSize(Size size) {
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
}
