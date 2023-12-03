package com.example.webwork.controllers;

import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.AddUserDto;
import com.example.webwork.except.UsersNotFoundException;
import com.example.webwork.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UsersService userService;
    public UserController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping()
    Iterable<UsersDTO> getAllUsers() {return userService.getAll();}

    @GetMapping("/{id}")
    UsersDTO get(@PathVariable String id) {return userService.findById(id).orElseThrow(() -> new UsersNotFoundException(id));}

    @PostMapping()
    UsersDTO createUser(@RequestBody UsersDTO users) {
        return userService.registerUser(users);
    }

    @ModelAttribute("userModel")
    public AddUserDto initBrand() {
        return new AddUserDto();
    }
    @GetMapping("/add")
    public String addUser() {
        return "user-add";
    }

    @PostMapping("/add")
    public String addUser(@Valid AddUserDto userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel",
                    bindingResult);
            return "redirect:/users/add";
        }
        userService.addUser(userModel);

        return "redirect:/";
    }


    @PutMapping()
    UsersDTO updateUser(@RequestBody UsersDTO users) {
        return userService.update(users);
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("usersInfo", userService.allUsers());

        return "user-all";
    }
    @GetMapping("/user-details/{user-userName}")
    public String userDetails(@PathVariable("user-userName") String userName, Model model) {
        model.addAttribute("userDetails", userService.userDetails(userName));

        return "user-details";
    }

    @GetMapping("/user-delete/{user-userName}")
    public String deleteUser(@PathVariable("user-userName") String userName) {
        userService.removeUser(userName);

        return "redirect:/users/all";
    }



}
