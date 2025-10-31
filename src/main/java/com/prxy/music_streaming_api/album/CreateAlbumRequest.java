package com.prxy.music_streaming_api.album;

public record CreateAlbumRequest(
        String title,
        Long artistId,
        String releaseDate
) {
}
