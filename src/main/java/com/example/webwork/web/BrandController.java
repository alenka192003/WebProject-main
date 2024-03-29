package com.example.webwork.web;

import com.example.webwork.dto.AddBrandDto;
import com.example.webwork.dto.ShowModelInfoDto;
import com.example.webwork.dto.UpdateBrandDto;
import com.example.webwork.models.Brand;
import com.example.webwork.repo.BrandRepository;
import com.example.webwork.services.BrandService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final BrandService brandService;
    private final BrandRepository brandRepository;
    public BrandController(BrandService brandService, BrandRepository brandRepository) {
        this.brandService = brandService;
        this.brandRepository = brandRepository;
    }

    @GetMapping("/all")
    public String showAllBrands(Principal principal,Model model) {
        LOG.log(Level.INFO,"Show all brands for "+ principal.getName());
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

    @GetMapping("/update/{brand-name}")
    public String updateBrandForm(@PathVariable("brand-name") String name, Model model) {
        Brand brand = brandRepository.findByName(name).orElse(null);
        model.addAttribute("brand", brand);
        model.addAttribute("updateBrandForm", new UpdateBrandDto());
        return "/brand/brand-update";
    }

    @PostMapping("/update/{brand-name}")
    public String updateBrand(@PathVariable("brand-name") String name, @Valid UpdateBrandDto updateBrandDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateBrandDto", updateBrandDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateBrandDto",
                    bindingResult);
            return "redirect:/brands/update/" + name;
        }

        brandService.updateBrand(name, updateBrandDto);
        return "redirect:/brands/all";
    }
}
