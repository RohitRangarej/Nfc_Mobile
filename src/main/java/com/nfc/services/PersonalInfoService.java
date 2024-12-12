package com.nfc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nfc.entities.PersonalInfo;
import com.nfc.repository.PersonalInfoRepository;

import java.util.List;

@Service
public class PersonalInfoService {

    @Autowired
    private PersonalInfoRepository repository;

    public PersonalInfo createOrUpdatePersonalInfo(PersonalInfo info) {
        return repository.save(info);
    }

    public PersonalInfo getPersonalInfoById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<PersonalInfo> getAllPersonalInfo() {
        return repository.findAll();
    }

    public void deletePersonalInfo(String id) {
        repository.deleteById(id);
    }

    public void addToGallery(String id, String imageUrl) {
        PersonalInfo info = getPersonalInfoById(id);
        if (info != null) {
            info.getGallery().add(imageUrl);
            repository.save(info);
        }
    }

    public void removeFromGallery(String id, String imageUrl) {
        PersonalInfo info = getPersonalInfoById(id);
        if (info != null) {
            info.getGallery().remove(imageUrl);
            repository.save(info);
        }
    }

    public void addSocialLink(String id, String socialLink) {
        PersonalInfo info = getPersonalInfoById(id);
        if (info != null) {
            info.getSocialLinks().add(socialLink);
            repository.save(info);
        }
    }

    public void removeSocialLink(String id, String socialLink) {
        PersonalInfo info = getPersonalInfoById(id);
        if (info != null) {
            info.getSocialLinks().remove(socialLink);
            repository.save(info);
        }
    }
}
