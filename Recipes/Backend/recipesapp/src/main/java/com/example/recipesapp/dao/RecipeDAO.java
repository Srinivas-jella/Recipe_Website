package com.example.recipesapp.dao;

import com.example.recipesapp.dto.RecipeDTO;
import com.example.recipesapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RecipeDAO {

    @Autowired
    private RecipeRepository recipeRepository;

    public Optional<RecipeDTO> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public List<RecipeDTO> searchRecipes(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public RecipeDTO saveRecipe(RecipeDTO recipe) {
        return recipeRepository.save(recipe);
    }
}
