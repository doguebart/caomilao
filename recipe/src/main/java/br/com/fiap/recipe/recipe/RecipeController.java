package br.com.fiap.recipe.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipe/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (recipeService.delete(id)) {
            redirectAttributes.addFlashAttribute("success", "Recipe deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Recipe not found");
        }
        return "redirect:/recipe";
    }
}
