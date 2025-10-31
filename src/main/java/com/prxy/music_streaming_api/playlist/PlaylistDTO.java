package com.prxy.music_streaming_api.playlist;

import com.prxy.music_streaming_api.song.SongSummaryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public record PlaylistDTO(
        Long id,
        String name,
        String description,
        List<SongSummaryDTO> songs
) {
}
