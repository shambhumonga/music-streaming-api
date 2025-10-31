package com.prxy.music_streaming_api.album;

import com.prxy.music_streaming_api.artist.ArtistMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    @Mapping(target = "artist", source = "artist")
    AlbumDTO toAlbumDTO(Album album);

    AlbumSummaryDTO toAlbumSummaryDTO(Album album);

    @Mapping(target = "artist", ignore = true)
    Album toAlbumEntity(CreateAlbumRequest request);
}
