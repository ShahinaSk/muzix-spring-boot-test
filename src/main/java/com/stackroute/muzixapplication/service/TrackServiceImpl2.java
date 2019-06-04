package com.stackroute.muzixapplication.service;

import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import com.stackroute.muzixapplication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class TrackServiceImpl2 implements TrackService
{

    @Autowired
    private TrackRepository trackRepository;

    public TrackServiceImpl2(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException();
        }
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track updateTrackComment(Integer trackId, String trackComment) throws TrackNotFoundException {

        Track track;
        if (trackRepository.existsById(trackId)){
            track=trackRepository.findById(trackId).get();
            track.setTrackComment(trackComment);
            trackRepository.save(track);
            return track;
        }
        throw new TrackNotFoundException();
    }

    @Override
    public Track deleteTrack(Integer trackId) throws TrackNotFoundException {
        Track track;
        if (trackRepository.existsById(trackId)){
            track=trackRepository.findById(trackId).get();
            trackRepository.deleteById(trackId);
            return track;
        }
        throw new TrackNotFoundException();
    }

    @Override
    public Track findTrackByName(String name)throws TrackNotFoundException
    {
        Track track=null;
        track=trackRepository.findTrackByName(name);
        if(track==null)
        {
            throw new TrackNotFoundException();
        }
        return track;
    }

}
