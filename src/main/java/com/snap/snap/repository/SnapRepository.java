package com.snap.snap.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.snap.snap.model.Snap;

public interface SnapRepository extends MongoRepository<Snap, ObjectId> {
    
}
