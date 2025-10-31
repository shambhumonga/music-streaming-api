package com.prxy.music_streaming_api.song;

import com.prxy.music_streaming_api.album.AlbumMapper;
import com.prxy.music_streaming_api.artist.ArtistMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, AlbumMapper.class})
public interface SongMapper {

    @Mapping(target = "artists", source = "artists")
    @Mapping(target= "album", source = "album")
    SongDTO toSongDTO(Song song);

    SongSummaryDTO toSongSummaryDTO(Song song);

    @Mapping(target = "album", ignore = true)
    @Mapping(target="artists", ignore = true)
    Song toSongEntity(CreateSongRequest request);
}
