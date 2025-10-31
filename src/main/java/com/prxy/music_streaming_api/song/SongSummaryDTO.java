package com.prxy.music_streaming_api.song;

public record SongSummaryDTO(
        Long id,
        String title,
        String duration
) {
}
