package com.example.recipesapp.controller;

import com.example.recipesapp.dto.RecipeDTO;
import com.example.recipesapp.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public RecipeDTO addRecipe(@RequestBody RecipeDTO recipeDTO) {
        return recipeService.addRecipe(recipeDTO);
    }

    @GetMapping("/search")
    public List<RecipeDTO> searchRecipes(@RequestParam String name) {
        List<RecipeDTO> dbRecipes = recipeService.searchRecipes(name);
        List<RecipeDTO> apiRecipes = recipeService.fetchAllFromExternalApi(name);

        List<RecipeDTO> combined = new ArrayList<>(dbRecipes);

        for (RecipeDTO apiRecipe : apiRecipes) {
            boolean exists = dbRecipes.stream()
                    .anyMatch(r -> r.getName().equalsIgnoreCase(apiRecipe.getName()));
            if (!exists) {
                combined.add(apiRecipe);
            }
        }
        return combined;
    }

    @GetMapping("/{id}")
    public RecipeDTO getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/all")
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }
}
