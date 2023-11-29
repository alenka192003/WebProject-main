package com.example.webwork.controllers;

import com.example.webwork.dto.ModelDTO;
import com.example.webwork.dto.dtoss.AddModelDto;
import com.example.webwork.dto.dtoss.AddUserDto;
import com.example.webwork.enums.CategoryEnum;
import com.example.webwork.except.ModelConflictException_2;
import com.example.webwork.except.ModelNotFoundException;
import com.example.webwork.services.ModelService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
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

    @DeleteMapping("/{id}")
    void deleteModel(@PathVariable String id) {
        modelService.expel(id);
    }

    @PutMapping()
    ModelDTO update(@RequestBody ModelDTO model) {
        return modelService.update(model);
    }

    @GetMapping("/name/{name}")
    ModelDTO one(@PathVariable String name) throws Throwable {
        return (ModelDTO) modelService.findAllByName(name);
    }

    @ModelAttribute("model")
    public AddModelDto initModel() {
        return new AddModelDto();
    }
    @GetMapping("/add")
    public String addModel() {
        return "model-add";
    }

    @PostMapping("/add")
    public String addModel(@Valid AddModelDto model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("modelModel", model);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.model",
                    bindingResult);
            return "redirect:/models/add";
        }
        modelService.registerModel_1(model);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String showAllModel(Model model) {
        model.addAttribute("modelInfo", modelService.allModels());

        return "model-all";
    }

    @GetMapping("/model-details/{model-name}")
    public String modelDetails(@PathVariable("model-name") String modelName, Model model) {
        model.addAttribute("modelDetails", modelService.modelDetails(modelName));

        return "model-details";
    }

}
