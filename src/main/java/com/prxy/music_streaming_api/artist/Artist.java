package com.prxy.music_streaming_api.artist;

import com.prxy.music_streaming_api.song.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String genre;

    @ManyToMany(mappedBy = "artists")
    private List<Song> songs;
}
