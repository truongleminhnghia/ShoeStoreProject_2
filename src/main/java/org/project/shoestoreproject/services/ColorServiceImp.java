package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Color;
import org.project.shoestoreproject.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImp implements ColorService {

    @Autowired private ColorRepository colorRepository;

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getColorById(int id) {
        return colorRepository.findById(id).orElseThrow(() -> new RuntimeException("No color found"));
    }

    @Override
    public List<Color> getByProduct(int productId) {
        return colorRepository.findByProduct(productId);
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public Color getColorByName(String name) {
        return colorRepository.findByColorName(name);
    }

    @Override
    public boolean updateColor(int id, Color updateColor) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("No color found"));
        color.setColorName(updateColor.getColorName());
        colorRepository.save(color);
        return true;
    }

    @Override
    public boolean deleteColor(int id) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new RuntimeException("No color found"));
        colorRepository.delete(color);
        return true;
    }
}
