package com.example.demo.image;

import org.springframework.ui.Model;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

  @Autowired
  FilesStorageService storageService;

  @GetMapping("{filename}")
  public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename) {
    try {
        Resource file = storageService.load(filename);

        if (file.exists() && file.isReadable()) {
            String fileFormat = "";
            int dotIndex = filename.lastIndexOf(".");
            if (dotIndex != -1 && dotIndex < filename.length() - 1) {
                fileFormat = filename.substring(dotIndex + 1);}

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                                                        .filename(file.getFilename())
                                                        .build();

            headers.setContentDisposition(contentDisposition);

            if (fileFormat == "png") {
                headers.setContentType(MediaType.IMAGE_PNG);
            }

            return ResponseEntity.ok()
            .headers(headers)
            .body(file);
        } else {
            throw new RuntimeException("File cannot be read or does not exist.");
        }
    } catch (Exception e) {
        throw new RuntimeException("Could not retrieve the file: " + e.getMessage());
    }
}


  @PostMapping
  public ResponseEntity<String> uploadImage(Model model, @RequestParam("filename") String filename, @RequestParam("file") MultipartFile file) {
    String message = "";

    try {
      storageService.save(filename, file);

      message = "Uploaded the image successfully: " + file.getOriginalFilename() + " as " + filename;
      model.addAttribute("message", message);
    } catch (Exception e) {
      message = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      model.addAttribute("message", message);
    }

    return ResponseEntity.ok(message);
  }
}
