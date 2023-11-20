package com.example.hibernatebuoi38.controller;

import com.example.hibernatebuoi38.dto.SizeDto;
import com.example.hibernatebuoi38.service.ISizeService;
import com.mysql.fabric.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/sizes")
public class SizeController {
    private final ISizeService iSizeService;

    public SizeController(ISizeService iSizeService) {
        this.iSizeService = iSizeService;
    }

    @PostMapping("/")
    public ResponseEntity<String> insert(@RequestBody SizeDto sizeDto){
        this.iSizeService.insert(sizeDto);
        return ResponseEntity.ok("create success");
    }

    @GetMapping("/")
    public ResponseEntity<List<SizeDto>>getAll(){
        return ResponseEntity.ok(this.iSizeService.getAll());
    }

    @GetMapping("/{sizeId}")
    public ResponseEntity<SizeDto>getById(@PathVariable Long sizeId){
        return ResponseEntity.ok(this.iSizeService.getById(sizeId));
    }


    @PutMapping("/{sizeId}")
    public ResponseEntity<SizeDto>update(@PathVariable Long sizeId,@RequestBody SizeDto sizeDto){
        sizeDto.setId(sizeId);
        return ResponseEntity.ok(this.iSizeService.update(sizeDto));
    }
}
