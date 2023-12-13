package com.example.webwork.web;

import com.example.webwork.dto.BrandDTO;
import com.example.webwork.dto.ModelDTO;
import com.example.webwork.services.ModelService;
import com.example.webwork.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class HomeController {
    private final ModelService modelService;
    private final OfferService offerService;

    public HomeController(ModelService modelService, OfferService offerService) {
        this.modelService = modelService;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<ModelDTO> latestModels = modelService.getAllModelsSortedByYear();
        model.addAttribute("latestModels", latestModels);


        BrandDTO bestSellingBrand = offerService.getBestSellingBrand();
        model.addAttribute("bestSellingBrand", bestSellingBrand);


        return "index";
}

}
