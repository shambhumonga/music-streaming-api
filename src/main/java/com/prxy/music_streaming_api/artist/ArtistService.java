package com.prxy.music_streaming_api.artist;

import com.prxy.music_streaming_api.common.Exception.ArtistNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepo artistRepo;
    private final ArtistMapper artistMapper;

    public Page<ArtistDTO> getAllArtists(Pageable pageable) {
        Page<Artist> artists = artistRepo.findAll(pageable);
        return artists.map(artistMapper::toArtistDTO);
    }

    public ArtistDTO createArtist(CreateArtistRequest request) {
        Artist artist = artistMapper.toArtistEntity(request);
        Artist saved = artistRepo.save(artist);
        return artistMapper.toArtistDTO(saved);
    }

    @Transactional
    public ArtistDTO updateArtist(Long id, UpdateArtistRequest request) {
        Artist artist = artistRepo.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(id));

        request.name().ifPresent(artist::setName);
        request.genre().ifPresent(artist::setGenre);

        return artistMapper.toArtistDTO(artist);
    }

    @Transactional
    public void deleteArtist(Long id) {
        if (!artistRepo.existsById(id)) {
            throw new ArtistNotFoundException(id);
        }
        artistRepo.deleteById(id);
    }

    public ArtistDTO getArtistById(Long id) {
        Artist artist = artistRepo.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(id));
        return artistMapper.toArtistDTO(artist);
    }
}
