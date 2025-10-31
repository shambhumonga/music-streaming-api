package com.prxy.music_streaming_api.song;

import com.prxy.music_streaming_api.album.Album;
import com.prxy.music_streaming_api.artist.Artist;
import com.prxy.music_streaming_api.playlist.Playlist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String duration;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @ManyToMany
    @JoinTable(
            name = "song_artists",
            joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id")
    )
    private List<Artist> artists;

    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;
}
