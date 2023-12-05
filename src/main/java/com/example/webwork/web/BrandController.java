package com.example.webwork.web;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.dtoss.AddBrandDto;
import com.example.webwork.dto.dtoss.ShowModelInfoDto;
import com.example.webwork.except.BrandNotFoundException;
import com.example.webwork.models.Brand;
import com.example.webwork.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String showAllBrands(Model model) {
        model.addAttribute("brandInfo", brandService.allBrands());

        return "/brand/brand-all";
    }

    @GetMapping("/add")
    public String addBrand() {
        return "/brand/brand-add";
    }

    @DeleteMapping("/{id}")
    void deleteBrand(@PathVariable String id) {
        brandService.expel(id);
    }
    //валидация + логика Все бренды имеют свои модели
    //все варианты + офферы и модели

    @ModelAttribute("brandModel")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @PostMapping("/add")
    public String addBrand(@Valid AddBrandDto brandModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brandModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel",
                    bindingResult);
            return "redirect:/brands/add";
        }
        brandService.addBrand(brandModel);

        return "redirect:/";
    }

    @GetMapping("/brand-details/{brand-name}")
    public String brandDetails(@PathVariable("brand-name") String brandName, Model model) {
        model.addAttribute("brandDetails", brandService.brandDetails(brandName));
        List<ShowModelInfoDto> models = brandService.getModelsByBrand(brandName);
        model.addAttribute("models", models);
        return "brand/brand-details";
    }
    @GetMapping("/brand-delete/{brand-name}")
    public String deleteCompany(@PathVariable("brand-name") String brandName) {
        brandService.removeBrand(brandName);

        return "redirect:/brands/all";
    }
}
