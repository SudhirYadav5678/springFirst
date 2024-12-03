package com.sudhir.first.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sudhir.first.entity.JounarlEntry;
import com.sudhir.first.repository.JournalEntryRepo;

@Component
public class JournalEntryServices {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JounarlEntry jounarlEntry) {
        journalEntryRepo.save(jounarlEntry);
    }

    public List<JounarlEntry> getAllEntry() {
        return journalEntryRepo.findAll();
    }

    public Optional<JounarlEntry> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id) {
        journalEntryRepo.deleteById(id);
    }
}
