package com.prxy.music_streaming_api.album;

import com.prxy.music_streaming_api.artist.Artist;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    @Column(name = "release_date")
    private String releaseDate;
}
