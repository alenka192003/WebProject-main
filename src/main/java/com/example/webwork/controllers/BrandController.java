package com.example.webwork.controllers;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.dtoss.AddBrandDto;
import com.example.webwork.except.BrandNotFoundException;
import com.example.webwork.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{id}")
    BrandDTO getBrand(@PathVariable String id) {
        return brandService.findById(id).orElseThrow(() -> new BrandNotFoundException(id));
    }

    @GetMapping("/all")
    public String showAllBrands(Model model) {
        model.addAttribute("brandInfo", brandService.allBrands());

        return "brand-all";
    }

    @GetMapping("/add")
    public String addBrand() {
        return "brand-add";
    }

    @DeleteMapping("/{id}")
    void deleteBrand(@PathVariable String id) {
        brandService.expel(id);
    }

    @ModelAttribute("brand")
    public AddBrandDto initBrand() {
        return new AddBrandDto();
    }

    @PutMapping()
    BrandDTO updateBrand(@RequestBody BrandDTO brand) {
        return brandService.update(brand);
    }

    @PostMapping("/add")
    public String addBrand(@Valid AddBrandDto brand, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("brandModel", brand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brand",
                    bindingResult);
            return "redirect:/brands/add";
        }
        brandService.registerBrand_1(brand);

        return "redirect:/";
    }

    @GetMapping("/brand-details/{brand-name}")
    public String brandDetails(@PathVariable("brand-name") String brandName, Model model) {
        model.addAttribute("brandDetails", brandService.brandDetails(brandName));

        return "brand-details";
    }

}
