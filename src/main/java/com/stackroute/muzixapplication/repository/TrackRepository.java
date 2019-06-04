package com.stackroute.muzixapplication.repository;

import com.stackroute.muzixapplication.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer> {

    @Query("select t from Track t where LOWER(t.trackName)=Lower(:name)")
    public Track findTrackByName(String name);

}
