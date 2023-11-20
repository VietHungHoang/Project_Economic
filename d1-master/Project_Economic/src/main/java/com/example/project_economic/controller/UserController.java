package com.example.project_economic.controller;

import com.example.project_economic.config.UserInfoDetails;
import com.example.project_economic.dto.ProductDto;
import com.example.project_economic.entity.CategoryEntity;
import com.example.project_economic.entity.UserEntity;
import com.example.project_economic.jwt.JwtService;
import com.example.project_economic.response.ProductResponse;
import com.example.project_economic.service.CartItemService;
import com.example.project_economic.service.CategoryService;
import com.example.project_economic.service.ProductService;
import com.example.project_economic.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemService cartItemService;
    @GetMapping("/index")
    public String welcomePage(){
        return "index";
    }

    @GetMapping("/login")
    public String showFormLogin(Model model){
        model.addAttribute("userEntity" ,new UserEntity());
        return "login/index";
    }
    @GetMapping("/register")
    public String showFormRegister(Model model){
        model.addAttribute("userEntity" ,new UserEntity());
        return "register/index";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userEntity") UserEntity userEntity,Model model){
        try{
            this.userService.createUser(userEntity);
            return "login/index";
        }catch (Exception exception){

            model.addAttribute("error",exception.getMessage());
            return "register/index";
        }
    }

    @PostMapping("/home")
    public String loginPage(@ModelAttribute("userEntity") UserEntity userEntity, Model model){
        try{
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userEntity.getUsername(),
                    userEntity.getPassword()
            ));
//            System.out.println(principal.getName());
//            if(authentication.isAuthenticated()){
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                String token=this.jwtService.generateToken(authentication.getName());
//                model.addAttribute("token",token);
//                model.addAttribute("username",authentication.getName());
//                model.addAttribute("roles",authentication.getAuthorities().toString());
//                model.addAttribute("products",this.findByAllProductActive());
//                model.addAttribute("numbercart",this.cartItemService.countCart(1L));
//                return "home/index";
//
//            }

        }catch (Exception exception){
            model.addAttribute("error","Username or password is valid!");
            return "login/index";
        }
        model.addAttribute("error","Username or password is valid!");
        return "login/index";
    }
    @GetMapping("/fail")
    public String loginFail(Model model){
        model.addAttribute("error","Username or password is valid!");
        return "login/index";
    }
    @GetMapping("/product-detail")
    public String getproductdetail(){
        return "home/product-detail";
    }

    @GetMapping("/categories")
    public String getaddnew(Model model, Principal principal){
        if(principal==null){
            return "login/index";
        }
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("category",new CategoryEntity());
        model.addAttribute("allcategories",this.categoryService.findAll());
        return "home/addnew";
    }

    @GetMapping("/homepage")
    public String getHomeIndex(Model model,Principal principal){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userId",((UserInfoDetails)authentication.getPrincipal()).getUserId());
        model.addAttribute("username",principal.getName());
        model.addAttribute("products",this.findByAllProductActive());
        model.addAttribute("numbercart",this.cartItemService.countCart(((UserInfoDetails)(authentication.getPrincipal())).getUserId()));
        return "home/product-list";
    }
    public List<ProductResponse>findByAllProductActive(){
        return this.productService.findAllIsActived();
    }
}
