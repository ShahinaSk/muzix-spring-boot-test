package com.stackroute.muzixapplication.repository;

import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp() throws Exception {
        track=new Track();
        track.setTrackId(10);
        track.setTrackName("duniya");
        track.setTrackComment("very nice");
    }

    @After
    public void tearDown() throws Exception {
        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrackSuccess(){

        trackRepository.save(track);
        Track fetchTrack=trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(10,fetchTrack.getTrackId());
    }


    @Test
    public void testSaveTrackFailure(){
        trackRepository.save(track);
        Track newTrack=new Track(101,track.getTrackName(),track.getTrackComment());
        trackRepository.save(newTrack);
        Assert.assertNotEquals(newTrack,track);
    }

    @Test
    public void testFindTrackByNameSuccess() {
        trackRepository.save(track);
        Track fetchedTrack=trackRepository.findTrackByName(track.getTrackName());
        Assert.assertEquals(track.getTrackId(),fetchedTrack.getTrackId());
    }

    @Test
    public void testFindTrackByNameFailure() {
        Track testTrack=new Track(101,"gullyboy","nice");
        trackRepository.save(track);
        Track fetchedTrack=trackRepository.findTrackByName(track.getTrackName());
        Assert.assertNotEquals(testTrack.getTrackName(),fetchedTrack.getTrackName());
    }

    @Test
    public void testDeleteTrackSuccess(){
        trackRepository.save(track);
        trackRepository.deleteById(track.getTrackId());
        Assert.assertFalse(trackRepository.existsById(track.getTrackId()));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDeleteTrackFailure(){
        //trying to delete the track which is not present in the trackRepository
        trackRepository.deleteById(track.getTrackId());
        Assert.assertFalse(trackRepository.existsById(track.getTrackId()));
    }
}