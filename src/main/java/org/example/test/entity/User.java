package org.example.test.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

     @Id
    private ObjectId id;



    // using this searching would be fast and username would be unique
     @Indexed(unique = true)
     @NonNull
    private   String userName;
     @NonNull
    private   String password;
     private List<String>roles;;
    @DBRef
    private List<DemoEntry>demoEntries=new ArrayList<>();

     /*DBref annotation is used to create reference of demoentry into user
     // mean the list conatins the refernce of demoentries which are present in DemoEntry
     // DBRef ("demoentries",objectid("xyz")
     // create link between user and Entry collection
     // bydefault indexing is not done  by springboot we have to do it manually*/

     // both password and username should not be null


}
