package com.prxy.music_streaming_api.playlist;

import com.prxy.music_streaming_api.song.Song;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "playlist_songs",
            joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id")
    )
    private List<Song> songs;
}
