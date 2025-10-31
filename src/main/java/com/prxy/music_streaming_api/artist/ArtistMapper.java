package com.prxy.music_streaming_api.artist;

import com.prxy.music_streaming_api.song.SongMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    ArtistDTO toArtistDTO(Artist artist);

    ArtistSummaryDTO toArtistSummaryDTO(Artist artists);

    Artist toArtistEntity(CreateArtistRequest request);
}
