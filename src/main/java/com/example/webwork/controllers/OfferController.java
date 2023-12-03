package com.example.webwork.controllers;

import com.example.webwork.dto.OfferDTO;
import com.example.webwork.dto.dtoss.AddOfferDto;
import com.example.webwork.except.OfferNotFoundException;
import com.example.webwork.services.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    public OfferController(OfferService offerService) {this.offerService = offerService;}

    @GetMapping()
    Iterable<OfferDTO> getAllOffers() {return offerService.getAll();}

    @GetMapping("/{id}")
    OfferDTO getOffer(@PathVariable String id) {return offerService.findById(id).orElseThrow(() -> new OfferNotFoundException(id));}

    @PostMapping()
    OfferDTO createOffer(@RequestBody OfferDTO offer) {
        return offerService.registerOffer(offer);
    }

    @DeleteMapping("/{id}")
    void deleteOffer(@PathVariable String id) {
        offerService.expel(id);
    }

    @PutMapping()
    OfferDTO updateOffer(@RequestBody OfferDTO offer) {
        return offerService.update(offer);
    }

    @ModelAttribute("offerModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }
    @GetMapping("/add")
    public String addOffer() {
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer",
                    bindingResult);
            return "redirect:/offers/add";
        }
        offerService.addOffer(offerModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllOffers(Model model) {
        model.addAttribute("offerInfo", offerService.allOffers());

        return "offer-all";
    }

    @GetMapping("/offer-details/{offer-model}")
    public String offerDetails(@PathVariable("offer-model") OfferDTO offer, Model model) {
        model.addAttribute("offerDetails", offerService.offerDetails(offer.getModel()));

        return "offer-details";
    }

}
