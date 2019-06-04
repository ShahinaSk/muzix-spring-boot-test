package com.stackroute.muzixapplication.service;

import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks();

    public Track updateTrackComment(Integer trackId,String trackComment) throws TrackNotFoundException;

    public Track deleteTrack(Integer trackId) throws TrackNotFoundException;

    public Track findTrackByName(String trackName) throws TrackNotFoundException;
}
