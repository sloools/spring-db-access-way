package me.song.enum1.controller;

import lombok.RequiredArgsConstructor;
import me.song.enum1.enum1.EnumMapperFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnumController {

    private final EnumMapperFactory enumMapperFactory;

    @GetMapping("/status")
    public ResponseEntity status(){
        return ResponseEntity.ok(enumMapperFactory.get("Status"));
    }
}
