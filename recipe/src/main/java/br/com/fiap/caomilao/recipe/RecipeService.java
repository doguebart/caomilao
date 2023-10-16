package br.com.fiap.caomilao.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public List<Recipe> findAll(){ return recipeRepository.findAll(); }

    public boolean delete(Long id) {
        var recipe = recipeRepository.findById(id);

        if(recipe.isEmpty()) return false;

        recipeRepository.deleteById(id);
        return true;
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
