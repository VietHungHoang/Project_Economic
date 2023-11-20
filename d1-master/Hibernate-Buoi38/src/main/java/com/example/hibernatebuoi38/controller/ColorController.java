package com.example.hibernatebuoi38.controller;

import com.example.hibernatebuoi38.dto.ColorDTO;
import com.example.hibernatebuoi38.service.IColorService;
import com.mysql.fabric.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/colors")
public class ColorController {
    private final IColorService iColorService;
    public ColorController(IColorService iColorService){
        this.iColorService=iColorService;
    }

    @PostMapping("/")
    public ResponseEntity<String>create(@RequestBody ColorDTO colorDTO){
        this.iColorService.save(colorDTO);
        return ResponseEntity.ok("create success");
    }

    @GetMapping("/")
    public ResponseEntity<List<ColorDTO>>getAll(){
        return ResponseEntity.ok(this.iColorService.getAll());
    }

    @GetMapping("/{colorId}")
    public ResponseEntity<ColorDTO>getById(@PathVariable Long colorId){
        return ResponseEntity.ok(this.iColorService.findById(colorId));
    }
}
