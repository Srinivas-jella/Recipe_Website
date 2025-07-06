package com.example.recipesapp.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipes")
public class RecipeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String area;
    private String imageUrl;

    @Column(length = 5000)
    private String instructions;
    private String youtubeUrl;
}
