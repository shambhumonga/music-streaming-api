package com.prxy.music_streaming_api.artist;

import java.util.Optional;

public record UpdateArtistRequest(
        Optional<String> name,
        Optional<String> genre
) {
}
