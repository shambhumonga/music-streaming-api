package com.prxy.music_streaming_api.artist;

public record CreateArtistRequest(
        String name,
        String genre
) {
}
