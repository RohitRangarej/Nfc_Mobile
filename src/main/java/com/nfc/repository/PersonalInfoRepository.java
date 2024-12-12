package com.nfc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nfc.entities.PersonalInfo;

public interface PersonalInfoRepository extends MongoRepository<PersonalInfo, String> {
}
