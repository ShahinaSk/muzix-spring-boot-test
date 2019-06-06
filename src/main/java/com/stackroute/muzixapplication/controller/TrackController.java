package com.stackroute.muzixapplication.controller;


import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import com.stackroute.muzixapplication.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    @Autowired
    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException
    {

        ResponseEntity responseEntity;
        Track savedTrack=null;
        savedTrack=trackService.saveTrack(track);
        responseEntity=new ResponseEntity<Track>(savedTrack, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()
    {
        return  new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @PutMapping("track/{id}/{comment}")
    public ResponseEntity<?> getTrackAfterUpdatingComments( @PathVariable Integer id,@PathVariable  String comment) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;
        responseEntity=new ResponseEntity<Track>(trackService.updateTrackComment(id, comment), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable Integer id) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;
        responseEntity=new ResponseEntity<Track>(trackService.deleteTrack(id), HttpStatus.OK);
        return responseEntity;
    }


}
