package org.example.test.Respository;

import org.bson.types.ObjectId;
import org.example.test.entity.DemoEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoEntryRepository  extends MongoRepository<DemoEntry, ObjectId>{
}

// controller-->service-->repository
// interface provided by spring mongodb and extends that