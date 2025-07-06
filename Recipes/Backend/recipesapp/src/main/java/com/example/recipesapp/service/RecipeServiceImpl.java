package com.example.recipesapp.service;

import com.example.recipesapp.dao.RecipeDAO;
import com.example.recipesapp.dto.RecipeDTO;
import com.example.recipesapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeDAO recipeDAO;

    @Override
    public List<RecipeDTO> searchRecipes(String name) {
        return recipeDAO.searchRecipes(name);
    }

    @Override
    public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
        return recipeDAO.saveRecipe(recipeDTO);
    }

    @Override
    public List<RecipeDTO> getAllRecipes() {
        return recipeDAO.getAllRecipes();
    }

    @Override
    public RecipeDTO getRecipeById(Long id) {
        return recipeDAO.getRecipeById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));
    }

    @Override
    public List<RecipeDTO> fetchAllFromExternalApi(String name) {
        List<RecipeDTO> result = new ArrayList<>();
        try {
            String apiUrl = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + name;
            RestTemplate restTemplate = new RestTemplate();
            Map response = restTemplate.getForObject(apiUrl, Map.class);

            if (response != null && response.get("meals") != null) {
                List<Map<String, Object>> meals = (List<Map<String, Object>>) response.get("meals");

                for (Map<String, Object> meal : meals) {
                    RecipeDTO recipe = new RecipeDTO();
                    recipe.setName((String) meal.get("strMeal"));
                    recipe.setCategory((String) meal.get("strCategory"));
                    recipe.setArea((String) meal.get("strArea"));
                    recipe.setImageUrl((String) meal.get("strMealThumb"));
                    recipe.setInstructions((String) meal.get("strInstructions"));
                    recipe.setYoutubeUrl((String) meal.get("strYoutube"));
                    result.add(recipe);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
