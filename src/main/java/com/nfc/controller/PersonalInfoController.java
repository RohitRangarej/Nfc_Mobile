package com.nfc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nfc.entities.PersonalInfo;
import com.nfc.entities.PersonalInfoDTO;
import com.nfc.services.PersonalInfoService;

import java.util.List;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService service;

    @PostMapping
    public ResponseEntity<PersonalInfo> createOrUpdate(@RequestBody PersonalInfo info) {
        return ResponseEntity.ok(service.createOrUpdatePersonalInfo(info));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalInfo> getById(@PathVariable String id) {
        PersonalInfo info = service.getPersonalInfoById(id);
        if (info != null) {
            return ResponseEntity.ok(info);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonalInfo>> getAll() {
        return ResponseEntity.ok(service.getAllPersonalInfo());
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<PersonalInfoDTO>> getIdNameEmailList() {
        List<PersonalInfo> personalInfoList = service.getAllPersonalInfo();
        
        // Map the PersonalInfo entities to PersonalInfoDTO
        List<PersonalInfoDTO> dtoList = personalInfoList.stream()
            .map(info -> new PersonalInfoDTO(
                info.getId(),
                info.getFirstName() + " " + info.getLastName(), // Combine first and last name
                info.getEmail()))
            .toList();

        return ResponseEntity.ok(dtoList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        service.deletePersonalInfo(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @PutMapping("/{id}/gallery/add")
    public ResponseEntity<String> addToGallery(@PathVariable String id, @RequestBody String imageUrl) {
        service.addToGallery(id, imageUrl);
        return ResponseEntity.ok("Image added to gallery");
    }

    @PutMapping("/{id}/gallery/remove")
    public ResponseEntity<String> removeFromGallery(@PathVariable String id, @RequestBody String imageUrl) {
        service.removeFromGallery(id, imageUrl);
        return ResponseEntity.ok("Image removed from gallery");
    }

    @PutMapping("/{id}/social-links/add")
    public ResponseEntity<String> addSocialLink(@PathVariable String id, @RequestBody String socialLink) {
        service.addSocialLink(id, socialLink);
        return ResponseEntity.ok("Social link added");
    }

    @PutMapping("/{id}/social-links/remove")
    public ResponseEntity<String> removeSocialLink(@PathVariable String id, @RequestBody String socialLink) {
        service.removeSocialLink(id, socialLink);
        return ResponseEntity.ok("Social link removed");
    }
}
