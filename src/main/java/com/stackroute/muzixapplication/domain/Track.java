package com.stackroute.muzixapplication.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Setter
@Getter
public class Track{

    @Id
    private int trackId;
    private String trackName;
    private String trackComment;

}
