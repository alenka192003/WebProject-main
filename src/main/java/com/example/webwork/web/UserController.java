package com.example.webwork.web;

import com.example.webwork.dto.UsersDTO;
import com.example.webwork.dto.dtoss.*;
import com.example.webwork.except.UsersNotFoundException;
import com.example.webwork.models.Users;
import com.example.webwork.repo.UsersRepository;
import com.example.webwork.services.UsersService;
import jakarta.validation.Valid;
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
    private final UsersRepository usersRepository;
    public UserController(UsersService userService, UsersRepository usersRepository) {
        this.userService = userService;
        this.usersRepository = usersRepository;
    }

    @GetMapping()
    Iterable<UsersDTO> getAllUsers() {return userService.getAll();}

    @GetMapping("/{id}")
    UsersDTO get(@PathVariable String id) {return userService.findById(id).orElseThrow(() -> new UsersNotFoundException(id));}

    @ModelAttribute("userModel")
    public AddUserDto initUser() {
        return new AddUserDto();
    }
    @GetMapping("/add")
    public String addUser() {
        return "/user/user-add";
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


    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("usersInfo", userService.allUsers());

        return "user/user-all";
    }
    @GetMapping("/user-details/{user-userName}")
    public String userDetails(@PathVariable("user-userName") String userName, Model model) {
        model.addAttribute("userDetails", userService.userDetails(userName));
        List<ShowInfoOffer> offers = userService.getOffersByUserName(userName);
        model.addAttribute("offers", offers);
        return "user/user-details";
    }

    @GetMapping("/user-delete/{user-userName}")
    public String deleteUser(@PathVariable("user-userName") String userName) {
        userService.removeUser(userName);

        return "redirect:/users/all";
    }
    @GetMapping("/update/{user-userName}")
    public String updateUserForm(@PathVariable("user-userName") String userName, Model model) {
        Users user = usersRepository.findByUserName(userName);
        model.addAttribute("user", user);
        model.addAttribute("updateUserModel", new UpdateUserDto());
        return "/user/user-update";
    }

    @PostMapping("/update/{user-userName}")
    public String updateUser(@PathVariable("user-userName") String userName, @Valid UpdateUserDto updateUserDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateUserModel", updateUserDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateUserModel",
                    bindingResult);
            return "redirect:/users/update/" + userName;
        }

        userService.updateUser(userName, updateUserDto);
        return "redirect:/users/all";
    }

}


