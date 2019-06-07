package com.stackroute.muzixapplication.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track{

    @Id
    private int trackId;
    private String trackName;
    private String trackComment;


}
