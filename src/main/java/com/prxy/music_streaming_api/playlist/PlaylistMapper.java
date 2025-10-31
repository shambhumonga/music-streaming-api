package com.prxy.music_streaming_api.playlist;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    @Mapping(target = "songs", source = "songs")
    PlaylistDTO toPlaylistDTO(Playlist playlist);

    PlaylistSummaryDTO toPlaylistSummaryDTO(Playlist playlist);

    @Mapping(target = "songs", ignore = true)
    Playlist toPlaylistEntity(CreatePlaylistRequest request);
}
