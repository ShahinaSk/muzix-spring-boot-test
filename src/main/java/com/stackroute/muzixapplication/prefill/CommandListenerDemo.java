package com.stackroute.muzixapplication.prefill;

import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandListenerDemo implements CommandLineRunner {

    @Value("${track2.id}")
    private int id;
    @Value("${track2.name}")
    private String name;
    @Value("${track2.comment}")
    private String comment;
    @Autowired
    TrackRepository trackRepository;
    Track track=new Track();


    @Override
    public void run(String... args) throws Exception {

        track.setTrackId(id);
        track.setTrackName(name);
        track.setTrackComment(comment);
        trackRepository.save(track);

    }
}
