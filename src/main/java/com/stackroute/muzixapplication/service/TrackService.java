package com.stackroute.muzixapplication.service;

import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {

     Track saveTrack(Track track) throws TrackAlreadyExistsException;

     List<Track> getAllTracks();

     Track updateTrackComment(Integer trackId,String trackComment) throws TrackNotFoundException;

     Track deleteTrack(Integer trackId) throws TrackNotFoundException;

/*
    public Track findTrackByName(String trackName) throws TrackNotFoundException;
*/
}
