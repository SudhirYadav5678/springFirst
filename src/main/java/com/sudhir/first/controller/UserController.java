package com.sudhir.first.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudhir.first.entity.UserEntity;
import com.sudhir.first.services.UserServices;

@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<UserEntity> getAllUser() {
        // return new ArrayList<>(jurnalEntry.values());
        return userServices.getAllEntry();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable ObjectId id) {
        Optional<UserEntity> journalEntity = userServices.findById(id);
        if (journalEntity.isPresent()) {
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public UserEntity createEntry(@RequestBody UserEntity myEntry) {
        // jurnalEntry.put(myEntry.getId(), myEntry);
        userServices.saveEntry(myEntry);
        return myEntry;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id) {
        userServices.deleteById(id);
        return true;
    }

    @PutMapping
    public ResponseEntity<?> UpdateEntry(@RequestBody UserEntity user) {
        UserEntity userInDB = userServices.findByUserName(user.getUserName());
        if (userInDB != null) {
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userServices.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
