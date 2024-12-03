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

import com.sudhir.first.entity.JounarlEntry;
import com.sudhir.first.entity.UserEntity;
import com.sudhir.first.services.JournalEntryServices;
import com.sudhir.first.services.UserServices;

@RestController
@RequestMapping("/journal")
public class journalEntryController {
    // Database like map created
    // private Map<Long, JounarlEntry> jurnalEntry = new HashMap();

    @Autowired
    private JournalEntryServices journalEntryServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("{user}")
    public ResponseEntity<?> getAllJournaEntriesfoUsers(@PathVariable String userName) {
        // return new ArrayList<>(jurnalEntry.values());
        UserEntity userInDB = userServices.findByUserName(userName);
        List<JounarlEntry> all = userInDB.getJounarlEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JounarlEntry> getById(@PathVariable ObjectId id) {
        Optional<JounarlEntry> journalEntity = journalEntryServices.findById(id);
        if (journalEntity.isPresent()) {
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{user}")
    public JounarlEntry createEntry(@RequestBody JounarlEntry myEntry, @PathVariable String userName) {
        // jurnalEntry.put(myEntry.getId(), myEntry);
        journalEntryServices.saveEntry(myEntry);
        return myEntry;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id) {
        journalEntryServices.deleteById(id);
        return true;
    }

    @PutMapping("/id/{id}")
    public JounarlEntry UpdateEntry(@PathVariable ObjectId id, @RequestBody JounarlEntry updatedEntry) {
        JounarlEntry old = journalEntryServices.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(updatedEntry.getTitle() != null && updatedEntry.getTitle().equals("") ? updatedEntry.getTitle()
                    : old.getTitle());

            old.setContent(updatedEntry.getContent() != null && updatedEntry.getContent().equals("")
                    ? updatedEntry.getContent()
                    : old.getContent());
        }
        journalEntryServices.saveEntry(old);
        return old;
    }
}
