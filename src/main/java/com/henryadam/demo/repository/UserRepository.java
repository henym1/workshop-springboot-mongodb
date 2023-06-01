package com.henryadam.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.henryadam.demo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	
	
	
}
