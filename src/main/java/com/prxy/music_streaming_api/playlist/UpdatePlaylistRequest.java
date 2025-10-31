package com.prxy.music_streaming_api.playlist;

import java.util.List;
import java.util.Optional;

public record UpdatePlaylistRequest(
        Optional<String> name,
        Optional<String> description
//        Optional<List<Long>> songIds
) {
}
