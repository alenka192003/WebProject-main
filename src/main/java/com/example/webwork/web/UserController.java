package com.example.webwork.web;

import com.example.webwork.dto.dtoss.*;
import com.example.webwork.models.Users;
import com.example.webwork.repo.UsersRepository;
import com.example.webwork.services.Impl.AuthService;
import com.example.webwork.services.OfferService;
import com.example.webwork.services.UsersService;
import com.example.webwork.view.UserProfileView;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final UsersService userService;
    private AuthService authService;

    private final UsersRepository usersRepository;

    public UserController(UsersService userService, UsersRepository usersRepository, AuthService authService) {
        this.userService = userService;
        this.usersRepository = usersRepository;
        this.authService = authService;
    }


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
    public String showAllUsers(Principal principal, Model model) {
        LOG.log(Level.INFO, "Show all users for " + principal.getName());
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
        Users user = usersRepository.findByUserName(userName).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("updateUserModel", new UpdateUserDto());
        return "user/user-update";
    }

    @PostMapping("/update/{user-userName}")
    public String updateUser(@PathVariable("user-userName") String userName, @Valid UpdateUserDto updateUserDto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateUserModel", updateUserDto);
            redirectAttributes.addFlashAttribute("updateUserModel", updateUserDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateUserModel",
                    bindingResult);
            return "redirect:/users/update/" + userName;
        }

        userService.updateUser(userName, updateUserDto);
        return "redirect:/users/all";
    }


    @GetMapping("/register")
    public String addUser(Model model) {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto initForm() {
        return new UserRegistrationDto();
    }

    @PostMapping("/register")
    public String createUser(@Valid UserRegistrationDto userRegistrationDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);
            return "redirect:/users/register";
        }
        authService.register(userRegistrationDto);

        return "redirect:/users/login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, userName);
        redirectAttributes.addFlashAttribute("badCredentials", true);
        System.out.println(userService.getUser(userName));
        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        Users user = userService.getUser(username);

        UserProfileView userProfileView = new UserProfileView(
                user.getUserName(),
                user.getEmail()
        );

        model.addAttribute("user", userProfileView);

        return "profile";
    }

    @GetMapping("/user-details-offer")
    public String showUserOffers(Principal principal, Model model) {
        String username = principal.getName();
        Users user = userService.getUser(username);
        List<ShowInfoOffer> offers = userService.getOffersByUserName(user.getUserName());
        LOG.info("User offers for {}: {}", principal.getName(), offers);
        model.addAttribute("offer", offers);
        return "user/user-offers";
    }
}


