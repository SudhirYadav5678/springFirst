package com.sudhir.first.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sudhir.first.entity.JounarlEntry;

public interface JournalEntryRepo extends MongoRepository<JounarlEntry, ObjectId> {

}
