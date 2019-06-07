package com.stackroute.muzixapplication.repository;

import com.stackroute.muzixapplication.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track,Integer> {

/*
    Track findTrackByName(String name);
*/

}
