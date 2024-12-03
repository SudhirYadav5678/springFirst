package com.sudhir.first.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "Journal")
// @Getter
// @Setter
@Data // the data is equal to the all getter setter and other annotations.
@NoArgsConstructor
public class JounarlEntry {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;

    // this is a getter and setter for annotiton @Getter and Setter;
    // public ObjectId getId() {
    // return id;
    // }

    // public void setId(ObjectId id) {
    // this.id = id;
    // }

}
