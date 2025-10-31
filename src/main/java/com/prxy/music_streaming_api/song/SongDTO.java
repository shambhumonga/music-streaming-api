package com.prxy.music_streaming_api.song;

import com.prxy.music_streaming_api.album.AlbumSummaryDTO;
import com.prxy.music_streaming_api.artist.ArtistSummaryDTO;

import java.util.List;

public record SongDTO(
        Long id,
        String title,
        List<ArtistSummaryDTO> artists,
        AlbumSummaryDTO album,
        String duration
) {
}
