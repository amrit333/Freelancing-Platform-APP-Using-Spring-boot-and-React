package com.freelancing.platform.repository;

import com.freelancing.platform.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    
    @Query("{ $or: [ { 'sender.$id' : ?0, 'receiver.$id' : ?1 }, { 'sender.$id' : ?1, 'receiver.$id' : ?0 } ] }")
    List<Message> findConversation(org.bson.types.ObjectId user1, org.bson.types.ObjectId user2, Sort sort);
}
