package com.example.hibernatebuoi38.controller;

import com.example.hibernatebuoi38.dto.CategoryDTO;
import com.example.hibernatebuoi38.service.ICategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {

    private final ICategoryService iCategoryService;


    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @PostMapping("/")
    public ResponseEntity<String> insert(@RequestBody() CategoryDTO categoryDTO){
        iCategoryService.save(categoryDTO);
        return ResponseEntity.ok("create success");
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>>getAll(){
        return ResponseEntity.ok(this.iCategoryService.getAll());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long categoryId){
        return ResponseEntity.ok(this.iCategoryService.findById(categoryId));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long categoryId,@RequestBody CategoryDTO categoryDTO){
        categoryDTO.setId(categoryId);
        return ResponseEntity.ok(this.iCategoryService.update(categoryDTO));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String>delete(@PathVariable Long id){
        this.iCategoryService.delete(id);
        return ResponseEntity.ok("delete success");
    }
}
