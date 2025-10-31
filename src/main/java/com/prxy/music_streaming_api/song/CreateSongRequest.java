package com.prxy.music_streaming_api.song;

import java.util.List;

public record CreateSongRequest(
        String title,
        Long albumId,
        List<Long> artistIds,
        String duration
) {
}
