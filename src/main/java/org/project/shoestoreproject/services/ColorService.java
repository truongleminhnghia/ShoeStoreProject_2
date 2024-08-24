package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Color;

import java.util.List;

public interface ColorService {
    public Color save(Color color);
    public Color getColorById(int id);
    public List<Color> getByProduct(int productId);
    public List<Color> getAllColors();
    public Color getColorByName(String name);
    public boolean updateColor(int id, Color color);
    public boolean deleteColor(int id);
}
