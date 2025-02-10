package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {

     User findByUserName(String name);

     void deleteByUserName(String name);

}
