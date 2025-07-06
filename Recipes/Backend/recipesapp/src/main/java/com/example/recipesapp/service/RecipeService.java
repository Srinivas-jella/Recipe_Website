package com.example.recipesapp.service;

import com.example.recipesapp.dto.RecipeDTO;
import java.util.List;

public interface RecipeService {

    List<RecipeDTO> searchRecipes(String name);

    RecipeDTO addRecipe(RecipeDTO recipeDTO);

    List<RecipeDTO> getAllRecipes();

    RecipeDTO getRecipeById(Long id);

    List<RecipeDTO> fetchAllFromExternalApi(String name);
}
