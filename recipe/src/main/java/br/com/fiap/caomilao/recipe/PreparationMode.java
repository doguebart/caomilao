package br.com.fiap.caomilao.recipe;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Embeddable
@Table(name = "preparation_mode")
public class PreparationMode {

    private int stepNumber;
    private String stepDescription;
}
