package com.example.webwork.controllers;

import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.AddBrandDto;
import com.example.webwork.dto.dtoss.AddUserDto;
import com.example.webwork.except.UsersNotFoundException;
import com.example.webwork.models.Users;
import com.example.webwork.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @ModelAttribute("user")
    public AddUserDto initBrand() {
        return new AddUserDto();
    }
    @GetMapping("/add")
    public String addUser() {
        return "user-add";
    }

    @PostMapping("/add")
    public String addUser(@Valid AddUserDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", user);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user",
                    bindingResult);
            return "redirect:/users/add";
        }
        userService.registerUser_1(user);

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable String id) {
        userService.expel(id);
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
}
