package ma.ensa.pfaproject.services;

import ma.ensa.pfaproject.entities.CategorieProduit;

import java.util.List;

public interface CategoryService {
    public CategorieProduit createCategory(String nom);
    public CategorieProduit updateCategory(Long id, CategorieProduit updatedCategory);

    public void deleteCategory(Long id);
    public CategorieProduit getCategoryById(Long id);
    public List<CategorieProduit> getAllCategory();

    public List<CategorieProduit> getAllCategoryByNomContainingKey(String key);
}
