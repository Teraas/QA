package com.famly.controllers;
import com.famly.entity.User;
import com.famly.entity.UserDetail;
import com.famly.repository.UserRepository;
import com.famly.services.MediaService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author harish.kumar-mbp
 * createdOn 20/08/23
 */
@RestController
@RequestMapping("/storage")
public class MediaController {
    @Value("${minio.bucket}")
    private String bucketName;

    @Autowired
    private MediaService mediaService;
    @Autowired
    private UserRepository userRepository;

    /***
     * Upload file to Object Store.
     * for Famly tree, imageName to be user_id_tree. MinIO hadnles name duplicate itself.
     * @param file
     * @return
     */
    @PostMapping("/upload/profile/")
    public ResponseEntity<String> uploadProfile(@RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) {
        User user = userRepository.findById(userId).get();
        UserDetail userDetails = user.getUserDetails();

        try {
            String fileName = file.getOriginalFilename();
            String uriObject = mediaService.uploadFile(bucketName, fileName, file.getInputStream());
            userDetails.setProfileLink(uriObject);
            userRepository.save(user);
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (Exception  e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file." + e.getMessage());
        }
    }

    @PostMapping("/upload/tree/")
    public ResponseEntity<String> uploadTreeFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) {
        User user = userRepository.findById(userId).get();
        UserDetail userDetails = user.getUserDetails();

        try {
            String fileName = file.getOriginalFilename();
            String uriObject = mediaService.uploadFile(bucketName, fileName, file.getInputStream());
            userDetails.setFamly_tree_link(uriObject);
            userRepository.save(user);
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (Exception  e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file." + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) {
        try {
            InputStream inputStream = mediaService.downloadFile(bucketName, fileName);
            InputStreamResource resource = new InputStreamResource(inputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
