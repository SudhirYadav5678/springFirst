package com.sudhir.first.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sudhir.first.entity.UserEntity;
import com.sudhir.first.repository.UserRepository;

@Component
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public List<UserEntity> getAllEntry() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
