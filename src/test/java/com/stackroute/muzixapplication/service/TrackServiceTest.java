package com.stackroute.muzixapplication.service;

import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import com.stackroute.muzixapplication.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    private Track track;
    //Create a mock for TrackRepository
    @Mock
    @Autowired
    private TrackRepository trackRepository;

    @InjectMocks
    private TrackServiceImpl2 trackServiceImpl;
    List<Track> trackList=null;

    private Optional optional;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        track=new Track(101,"gullyboy","nice");
        trackList=new ArrayList<>();
        trackList.add(track);
        optional=Optional.of(track);

    }

    @Test
    public void testSaveTrack() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack=trackServiceImpl.saveTrack(track);
        Assert.assertEquals(track,savedTrack);
        verify(trackRepository,times(1)).save(track);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void testSaveTrackFailure() throws TrackAlreadyExistsException
    {
        when(trackRepository.save(track)).thenReturn(null);
        when(trackRepository.existsById(track.getTrackId())).thenReturn(true);
        Track savedTrack= trackServiceImpl.saveTrack(track);

        verify(trackRepository,times(1)).save(track);

    }

    @Test
    public void testGetAllTracks() {
        when(trackRepository.findAll()).thenReturn(trackList);
        List<Track> tracks=trackServiceImpl.getAllTracks();
        Assert.assertEquals(tracks,trackList);
    }

    @Test
    public void testUpdateTrackSuccess() throws TrackNotFoundException {

        when(trackRepository.findById(any())).thenReturn(optional);
        trackRepository.save(track);
        String newComment="something";
        Assert.assertEquals(newComment,trackServiceImpl.updateTrackComment(track.getTrackId(),"something").getTrackComment());

    }

    @Test
    public void testUpdateTrackFailure() throws TrackNotFoundException {

        when(trackRepository.findById(any())).thenReturn(optional);
        String newComment=null;
        Assert.assertNull(trackServiceImpl.updateTrackComment(track.getTrackId(),newComment).getTrackComment());

    }

    @Test
    public void testDeleteTrack() throws TrackNotFoundException {
        when(trackRepository.findById(any())).thenReturn(optional);
        trackRepository.save(track);
        Assert.assertEquals(track,trackServiceImpl.deleteTrack(track.getTrackId()));

    }

    @Test(expected = TrackNotFoundException.class)
    public void testDeleteTrackFailure() throws TrackNotFoundException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Assert.assertEquals(track,trackServiceImpl.deleteTrack(track.getTrackId()));

    }

    @Test(expected = TrackNotFoundException.class)
    public void testFindTrackByNameFailure() throws TrackAlreadyExistsException, TrackNotFoundException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack=trackServiceImpl.saveTrack(track);
        String name=savedTrack.getTrackName();
        Assert.assertEquals(name,trackServiceImpl.findTrackByName(savedTrack.getTrackName()));
    }
}