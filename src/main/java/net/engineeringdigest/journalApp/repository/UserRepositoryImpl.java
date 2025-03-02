package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query =new Query();
        //query.addCriteria(Criteria.where("userName").is("vihaan"));
        //query.addCriteria(Criteria.where("email").ne(null).ne(""));

        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9]+([._-][0-9a-zA-Z]+)*@[a-zA-Z0-9]+([.-][0-9a-zA-Z]+)*\\.[a-zA-Z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
