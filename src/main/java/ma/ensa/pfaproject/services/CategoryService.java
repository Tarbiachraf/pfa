package ma.ensa.pfaproject.services;

import jdk.jfr.Category;
import ma.ensa.pfaproject.entities.CategorieProduit;

import java.util.List;

public interface CategoryService {
    public CategorieProduit createCategory(String nom);
    public CategorieProduit updateCategory(CategorieProduit updatedCategory);

    public void deleteCategory(Long id);
    public CategorieProduit getCategoryById(Long id);
    public List<CategorieProduit> getAllCategory();
}
