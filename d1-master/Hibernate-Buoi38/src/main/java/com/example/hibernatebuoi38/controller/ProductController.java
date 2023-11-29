package com.example.hibernatebuoi38.controller;

import com.example.hibernatebuoi38.dto.ProductDto;
import com.example.hibernatebuoi38.service.ICategoryService;
import com.example.hibernatebuoi38.service.IColorService;
import com.example.hibernatebuoi38.service.IProductService;
import com.example.hibernatebuoi38.service.ISizeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService iProductService;
    private final ICategoryService iCategoryService;
    private final ISizeService iSizeService;

    private final IColorService iColorService;
    public ProductController(IProductService iProductService, ICategoryService iCategoryService, ISizeService iSizeService, IColorService iColorService) {
        this.iProductService = iProductService;
        this.iCategoryService = iCategoryService;
        this.iSizeService = iSizeService;
        this.iColorService = iColorService;
    }

    @GetMapping("/save")
    public String showFormInsertProduct(Model model){
        model.addAttribute("product",new ProductDto());
        model.addAttribute("all_categories",this.iCategoryService.getAll());
        model.addAttribute("all_size",this.iSizeService.getAll());
        model.addAttribute("all_color",this.iColorService.getAll());
        return "insert";
    }

    @PostMapping("/save")
    public String insertProduct(@ModelAttribute ProductDto productDto,Model model){
        this.iProductService.insert(productDto);
        model.addAttribute("all_categories",this.iCategoryService.getAll());
        model.addAttribute("all_size",this.iSizeService.getAll());
        model.addAttribute("allProduct",this.iProductService.getAll());
        model.addAttribute("all_color",this.iColorService.getAll());
        return "home";
    }

    @GetMapping("/home")
    public String getHome(Model model){
        model.addAttribute("allProduct",this.iProductService.getAll());
        model.addAttribute("all_categories",this.iCategoryService.getAll());
        model.addAttribute("all_size",this.iSizeService.getAll());
        model.addAttribute("all_color",this.iColorService.getAll());
        return "home";
    }

    @GetMapping("/category/{categoryId}")
    public String getProductByCategoryId(@PathVariable Long categoryId,Model model){
        model.addAttribute("allProduct",this.iProductService.findByCategory(categoryId));
        model.addAttribute("all_categories",this.iCategoryService.getAll());
        model.addAttribute("all_size",this.iSizeService.getAll());
        model.addAttribute("all_color",this.iColorService.getAll());
        return "home";
    }

    @GetMapping("/size/{sizeId}")
    public String getProductBySize(@PathVariable Long sizeId,Model model){
        model.addAttribute("allProduct",this.iProductService.findBySize(sizeId));
        model.addAttribute("all_categories",this.iCategoryService.getAll());
        model.addAttribute("all_size",this.iSizeService.getAll());
        model.addAttribute("all_color",this.iColorService.getAll());
        return "home";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId,Model model){
        this.iProductService.delete(productId);
        model.addAttribute("allProduct",this.iProductService.getAll());
        model.addAttribute("all_categories",this.iCategoryService.getAll());
        model.addAttribute("all_size",this.iSizeService.getAll());
        model.addAttribute("all_color",this.iColorService.getAll());
        return "home";
    }
}
