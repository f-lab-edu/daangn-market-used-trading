package com.ssibongee.daangnmarket.image.controller;

import com.ssibongee.daangnmarket.commons.annotation.LoginRequired;
import com.ssibongee.daangnmarket.image.service.ImageFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ssibongee.daangnmarket.commons.HttpStatusResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostImageController {

    private final ImageFileUploadService imageFileUploadService;

    @LoginRequired
    @PostMapping("/{postId}/images")
    public ResponseEntity<HttpStatus> uploadImages(@PathVariable Long postId,
                                                   @RequestParam("file") List<MultipartFile> files) throws IOException {
        imageFileUploadService.upload(postId, files);

        return RESPONSE_OK;
    }
}
