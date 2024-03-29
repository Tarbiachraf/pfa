package ma.ensa.pfaproject.services.servicesImp;

import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.entities.CategorieProduit;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.CategoryRepository;
import ma.ensa.pfaproject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategorieProduit createCategory(String nom) {
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setNomCategorie(nom);
        return categoryRepository.save(categorieProduit);
    }

    @Override
    public CategorieProduit updateCategory(Long id, CategorieProduit updatedCategory) {
        CategorieProduit categorieProduit = categoryRepository.findById(id).orElseThrow(()->{
           return new RessourceNotFoundException(ResourceTypeConstant.CATEGORY,id, ErrorMessages.CategoryNotFoundMessage);
        });
        categorieProduit.setNomCategorie(updatedCategory.getNomCategorie());
        categorieProduit = categoryRepository.save(categorieProduit);
        return categorieProduit;
    }

    @Override
    public void deleteCategory(Long id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }
        else {
            throw new RessourceNotFoundException(ResourceTypeConstant.CATEGORY,id, ErrorMessages.CategoryNotFoundMessage);
        }
    }

    @Override
    public CategorieProduit getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.CATEGORY,id, ErrorMessages.CategoryNotFoundMessage));
    }

    @Override
    public List<CategorieProduit> getAllCategory() {
        List<CategorieProduit> categorieProduitList = categoryRepository.findAll();
        if(categorieProduitList == null){
            return Collections.emptyList();
        }
        return categorieProduitList;
    }

    @Override
    public List<CategorieProduit> getAllCategoryByNomContainingKey(String key) {
        List<CategorieProduit> categorieProduitList = categoryRepository.getAllCategorieProduitsByNomContainingKey(key);
        if(categorieProduitList == null){
            Collections.emptyList();
        }
        return categorieProduitList;
    }
}
