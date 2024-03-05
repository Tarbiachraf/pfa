package ma.ensa.pfaproject.services.servicesImp;

import jdk.jfr.Category;
import ma.ensa.pfaproject.constants.ErrorMessages;
import ma.ensa.pfaproject.constants.ResourceTypeConstant;
import ma.ensa.pfaproject.entities.CategorieProduit;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import ma.ensa.pfaproject.repositories.CategoryRepository;
import ma.ensa.pfaproject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CategorieProduit updateCategory(CategorieProduit updatedCategory) {
        CategorieProduit categorieProduit = categoryRepository.findById(updatedCategory.getIdCategorie()).orElseThrow(()->{
           return new RessourceNotFoundException(ResourceTypeConstant.CATEGORY,updatedCategory.getIdCategorie(), ErrorMessages.CategoryNotFoundMessage);
        });
        return categorieProduit;
    }

    @Override
    public void deleteCategory(Long id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        }
        throw new RessourceNotFoundException(ResourceTypeConstant.CATEGORY,id, ErrorMessages.CategoryNotFoundMessage);
    }

    @Override
    public CategorieProduit getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new RessourceNotFoundException(ResourceTypeConstant.CATEGORY,id, ErrorMessages.CategoryNotFoundMessage));
    }

    @Override
    public List<CategorieProduit> getAllCategory() {
        return categoryRepository.findAll();
    }
}
