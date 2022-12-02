package com.example.xsharktank.repository;

import com.example.xsharktank.model.Pitch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitchRepository extends MongoRepository<Pitch, String> {
}
