package com.stackroute.muzixapplication.startup;

import com.stackroute.muzixapplication.domain.Track;
import com.stackroute.muzixapplication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SeedData1 implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private final Environment environment;

    @Autowired
    TrackRepository trackRepository;
    Track track=new Track();

    public SeedData1(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        track.setTrackId(Integer.parseInt(environment.getProperty("track1.id")));
        track.setTrackName(environment.getProperty("track1.name"));
        track.setTrackComment(environment.getProperty("track1.comment"));
        trackRepository.save(track);

    }
}
