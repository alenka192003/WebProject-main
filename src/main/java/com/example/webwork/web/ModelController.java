package com.example.webwork.web;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.AddModelDto;
import com.example.webwork.except.ModelNotFoundException;
import com.example.webwork.services.BrandService;
import com.example.webwork.services.ModelService;
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

@Controller
@RequestMapping("/models")
public class ModelController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final ModelService modelService;
    private final BrandService brandService;
    public ModelController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping()
    Iterable<ModelDTO> getAllModels() {
        return modelService.getAll();
    }

    @GetMapping("/{id}")
    ModelDTO getModel(@PathVariable String id) {return modelService.findById(id).orElseThrow(() -> new ModelNotFoundException(id));}

    @PostMapping()
    ModelDTO createModel(@RequestBody ModelDTO model) {
        return modelService.registerModel(model);
    }

    @PutMapping()
    ModelDTO update(@RequestBody ModelDTO model) {
        return modelService.update(model);
    }

    @GetMapping("/name/{name}")
    ModelDTO one(@PathVariable String name) throws Throwable {
        return (ModelDTO) modelService.findAllByName(name);
    }

    @ModelAttribute("modelModel")
    public AddModelDto initModel() {
        return new AddModelDto();
    }

    @GetMapping("/add")
    public String addModel(Model model) {
        model.addAttribute("availableBrands", brandService.allBrands());
        return "/model/model-add";
    }


    @PostMapping("/add")
    public String addModel(@Valid AddModelDto modelModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", modelModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.modelModel",
                    bindingResult);
            return "redirect:/models/add";
        }
        modelService.addModel(modelModel);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllModel(Principal principal,Model model) {
        LOG.log(Level.INFO,"Show all models for "+ principal.getName());
        model.addAttribute("modelInfo", modelService.allModels());

        return "model/model-all";
    }

    @GetMapping("/model-details/{model-name}")
    public String modelDetails(@PathVariable("model-name") String modelName, Model model) {
        model.addAttribute("modelDetails", modelService.modelDetails(modelName));

        return "/model/model-details";
    }
    @GetMapping("/model-delete/{model-name}")
    public String deleteModel(@PathVariable("model-name") String modelName) {
        modelService.removeModel(modelName);

        return "redirect:/models/all";
    }

}
