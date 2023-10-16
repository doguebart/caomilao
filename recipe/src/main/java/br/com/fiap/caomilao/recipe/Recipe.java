package br.com.fiap.caomilao.recipe;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Recipe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String ingredientName;

    private double qty;

    private String unit;

    private int stepNumber;

    private String stepDescription;

    private String level;

    private String preparation_time;

}
