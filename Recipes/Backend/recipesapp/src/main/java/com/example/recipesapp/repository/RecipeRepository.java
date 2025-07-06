package com.example.recipesapp.repository;

import com.example.recipesapp.dto.RecipeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeDTO, Long> {

    List<RecipeDTO> findByNameContainingIgnoreCase(String name);
}
