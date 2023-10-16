package br.com.fiap.caomilao.recipe;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
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

    @DeleteMapping
    public String deleteobject(Recipe recipe){
        System.out.println(recipe);
        return "redirect:/recipe";
    }

    @GetMapping("/new")
    public String form(Recipe recipe) {
        return "recipe/form";
    }

    @PostMapping()
    public String save(@Valid Recipe recipe, BindingResult result, RedirectAttributes redirect) {
        System.out.println(recipe);
        if (result.hasErrors()) return "/recipe/form";
        recipeService.save(recipe);
        redirect.addFlashAttribute("success", "Tarefa cadastrada com sucesso");
        return "redirect:/recipe";
    }
}
