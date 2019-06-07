package com.stackroute.muzixapplication.service;

import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import com.stackroute.muzixapplication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@EnableCaching
public class TrackServiceImpl2 implements TrackService
{
    /*public void simulateDelay(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    @Autowired
    private TrackRepository trackRepository;

    public TrackServiceImpl2(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @CacheEvict(value = "track",allEntries = true)
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if (trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException();
        }
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

    @Cacheable("track")
    @Override
    public List<Track> getAllTracks() {
//        simulateDelay();
        List<Track> trackList=trackRepository.findAll();
        return trackList;
    }

    @CacheEvict(value = "track",allEntries = true)
    @Override
    public Track updateTrackComment(Integer trackId, String trackComment) throws TrackNotFoundException {

        Optional optional = trackRepository.findById(trackId);
        if (optional.isPresent()) {
            Track track = trackRepository.findById(trackId).get();
            track.setTrackComment(trackComment);
            trackRepository.save(track);
            return track;

        } else {
            throw new TrackNotFoundException();
        }
    }

    @CacheEvict(value = "track",allEntries = true)
    @Override
    public Track deleteTrack(Integer trackId) throws TrackNotFoundException {
        Track track;
        Optional optional = trackRepository.findById(trackId);
        if (optional.isPresent()){
            track=trackRepository.findById(trackId).get();
            trackRepository.deleteById(trackId);
            return track;
        }
        throw new TrackNotFoundException();
    }

    /*@Override
    public Track findTrackByName(String name)throws TrackNotFoundException
    {
        Track track=null;
        track=trackRepository.findTrackByName(name);
        if(track==null)
        {
            throw new TrackNotFoundException();
        }
        return track;
    }*/

}
