package br.com.fiap.caomilao.recipe;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Ingredient {
    private String ingredientName;
    private double qty;
    private String unit;
}
