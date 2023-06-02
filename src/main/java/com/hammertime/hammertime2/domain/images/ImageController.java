package com.hammertime.hammertime2.domain.images;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hammertime.hammertime2.exceptions.ImageNotFoundException;
import com.hammertime.hammertime2.service.IBackendService;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageController {
    @Autowired
    IBackendService backendService;

    // Get All Image Directories
    @GetMapping("/dataRequest/images")
    List<Image> getAllImage() {return backendService.getAllImage();}

    // Get Image Directory by ID
    @GetMapping("dataRequest/images/id/{id}")
    Image getImage(@PathVariable Long id) throws ImageNotFoundException{
        return backendService.getImage(id);
    }

    // Create Image Directory
    @PostMapping("dataRequest/newimage")
    Image newImage(@RequestBody Image image){
        return backendService.createImage(image);
    }

    // Delete Image Directory
    @DeleteMapping("dataRequest/deleteimage")
    void deleteImage(@PathVariable Long id) {
        backendService.deleteImage(id);
    }
}
