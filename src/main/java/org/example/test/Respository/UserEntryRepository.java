package org.example.test.Respository;

import org.bson.types.ObjectId;
import org.example.test.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
  User findByUserName(String username);
  public void deleteByUserName(String username);

}
