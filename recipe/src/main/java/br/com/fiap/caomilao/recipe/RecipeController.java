package br.com.fiap.caomilao.recipe;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("recipes", recipeService.findAll());
        return "recipe/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (recipeService.delete(id)) {
            redirectAttributes.addFlashAttribute("success", getMessage("recipe.delete.success"));
        } else {
            redirectAttributes.addFlashAttribute("error", getMessage("recipe.notfound"));
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
        redirect.addFlashAttribute("success", getMessage("recipe.create.success"));
        return "redirect:/recipe";
    }

    private String getMessage(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
