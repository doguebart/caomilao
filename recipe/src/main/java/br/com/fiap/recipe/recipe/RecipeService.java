package br.com.fiap.recipe.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public List<Recipe> findAll(){ return recipeRepository.findAll(); }

    public boolean delete(Long id) {
        var task = recipeRepository.findById(id);

        if(task.isEmpty()) return false;

        recipeRepository.deleteById(id);
        return true;
    }
}
