package com.prxy.music_streaming_api.album;

import com.prxy.music_streaming_api.artist.Artist;
import com.prxy.music_streaming_api.artist.ArtistRepo;
import com.prxy.music_streaming_api.common.Exception.AlbumNotFoundException;
import com.prxy.music_streaming_api.common.Exception.ArtistNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepo albumRepo;
    private final AlbumMapper albumMapper;
    private final ArtistRepo artistRepo;

    public Page<AlbumDTO> getAllAlbums(Pageable pageable) {
        Page<Album> albums = albumRepo.findAll(pageable);
        return albums.map(albumMapper::toAlbumDTO);
    }

    public AlbumDTO createAlbum(CreateAlbumRequest request) {
        Album album = albumMapper.toAlbumEntity(request);
        Artist artist = artistRepo.findById(request.artistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));
        album.setArtist(artist);
        Album saved = albumRepo.save(album);
        return albumMapper.toAlbumDTO(saved);
    }

    @Transactional
    public AlbumDTO updateAlbum(Long id, UpdateAlbumRequest request) {
        Album album = albumRepo.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id));
        request.title().ifPresent(album::setTitle);
        request.releaseDate().ifPresent(album::setReleaseDate);
        request.artistId().ifPresent(
            artistId -> {
                Artist artist = artistRepo.findById(artistId)
                        .orElseThrow(() -> new ArtistNotFoundException(artistId));
                album.setArtist(artist);
            }
        );
        return albumMapper.toAlbumDTO(album);
    }

    @Transactional
    public void deleteAlbum(Long id) {
        if (!albumRepo.existsById(id)) {
            throw new AlbumNotFoundException(id);
        }
        albumRepo.deleteById(id);
    }

    public AlbumDTO getAlbumById(Long id) {
        Album album = albumRepo.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id));
        return albumMapper.toAlbumDTO(album);
    }

    public Page<AlbumSummaryDTO> getAlbumsByArtistId(Long artistId, Pageable pageable) {
        Page<Album> albums = albumRepo.findByArtistId(artistId, pageable);
        if(albums.isEmpty()) {
            throw AlbumNotFoundException.withArtistId(artistId);
        }
        return albums.map(albumMapper::toAlbumSummaryDTO);
    }
}
