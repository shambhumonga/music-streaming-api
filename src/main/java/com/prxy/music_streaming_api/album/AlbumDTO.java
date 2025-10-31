package com.prxy.music_streaming_api.album;

import com.prxy.music_streaming_api.artist.ArtistSummaryDTO;

public record AlbumDTO(
        Long id,
        String title,
        ArtistSummaryDTO artist,
        String releaseDate
) {
}
