package com.prxy.music_streaming_api.playlist;

import java.util.List;

public record CreatePlaylistRequest(
        String name,
        String description
//        List<Long> songIds
) {
}
