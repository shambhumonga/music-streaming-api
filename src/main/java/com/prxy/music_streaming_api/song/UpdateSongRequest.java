package com.prxy.music_streaming_api.song;

import java.util.*;

public record UpdateSongRequest(
        Optional<String> title,
        Optional<Long> albumId,
        Optional<List<Long>> artistIds,
        Optional<String> duration
) {
}
