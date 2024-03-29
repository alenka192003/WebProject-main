package com.example.webwork.web;

import com.example.webwork.dto.OfferDTO;
import com.example.webwork.dto.AddOfferDto;
import com.example.webwork.dto.ShowInfoOffer;
import com.example.webwork.except.OfferNotFoundException;
import com.example.webwork.services.ModelService;
import com.example.webwork.services.OfferService;
import com.example.webwork.services.UsersService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final OfferService offerService;
    private final ModelService modelService;
    private final UsersService usersService;
    public OfferController(OfferService offerService, ModelService modelService, UsersService usersService) {this.offerService = offerService;
        this.modelService = modelService;
        this.usersService = usersService;
    }

    @GetMapping()
    Iterable<OfferDTO> getAllOffers() {return offerService.getAll();}

    @GetMapping("/{id}")
    OfferDTO getOffer(@PathVariable String id) {return offerService.findById(id).orElseThrow(() -> new OfferNotFoundException(id));}

    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("availableModels", modelService.allModels());
        model.addAttribute("availableUsers",usersService.allUsers());
        return "/offer/offer-add";
    }


    @PutMapping()
    OfferDTO updateOffer(@RequestBody OfferDTO offer) {
        return offerService.update(offer);
    }

    @ModelAttribute("offerModel")
    public AddOfferDto initOffer() {
        return new AddOfferDto();
    }


    @PostMapping("/add")
    public String addOffer(@Valid AddOfferDto offerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                    bindingResult);
            return "redirect:/offers/add";
        }
        offerService.addOffer(offerModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllOffers(Principal principal,Model model) {
        LOG.log(Level.INFO,"Show all offers for "+ principal.getName());
        List<ShowInfoOffer> allOffers = offerService.allOffers();
        BigDecimal totalProfit = offerService.getTotalProfit();
        model.addAttribute("offerInfo", offerService.allOffers());
        model.addAttribute("totalProfit", totalProfit);

        return "offer/offer-all";
    }

    @GetMapping("/offers-details/{offer-id}")
    public String offerDetails(@PathVariable("offer-id") String id, Model model) {
        model.addAttribute("offerDetails", offerService.offerDetails(id));

        return "offer/offer-details";
    }
    @GetMapping("/offer-delete/{offer-id}")
    public String deleteOffer(@PathVariable("offer-id") String id) {
        offerService.removeOffer(id);

        return "redirect:/offers/all";
    }
}