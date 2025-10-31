package com.prxy.music_streaming_api.album;

import java.util.Optional;

public record UpdateAlbumRequest(
        Optional<String> title,
        Optional<Long> artistId,
        Optional<String> releaseDate
) {
}
