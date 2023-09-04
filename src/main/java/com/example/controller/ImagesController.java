package com.example.controller;

import com.example.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImagesController {
    
    private final StorageService storageService;
    
    @GetMapping(value = "/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] getImage(@PathVariable("filename") String filename) throws IOException {
        Resource resource = storageService.loadAsResource(filename);
        return resource.getContentAsByteArray();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    String handleFileUpload(@RequestParam("file") final MultipartFile file) {
        return storageService.store(file);
    }
}
